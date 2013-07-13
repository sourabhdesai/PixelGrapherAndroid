/* 
 * Copyright 2012 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.PixelBin.CodeGeneration;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.Tree;

import com.google.dexmaker.BinaryOp;
import com.google.dexmaker.Code;
import com.google.dexmaker.Comparison;
import com.google.dexmaker.Label;
import com.google.dexmaker.Local;
import com.google.dexmaker.MethodId;
import com.google.dexmaker.TypeId;
import com.google.dexmaker.UnaryOp;
import com.example.PixelBin.CodeGeneration.ScriptGrammarLexer;
import com.example.PixelBin.CodeGeneration.ScriptGrammarParser;

public class DexCodeGenerator {
    static final boolean DEBUG = false;
	
	static Map<String, BinaryOp> BINARY_OPS = new HashMap<String, BinaryOp>();
	static Map<String, UnaryOp> UNARY_OPS = new HashMap<String, UnaryOp>();
	static Map<String, Comparison> COMPARISONS = new HashMap<String, Comparison>();
	static Map<Comparison, Comparison> COMPARISON_OPPOSITES = new HashMap<Comparison, Comparison>();

    static {
    	BINARY_OPS.put("+", BinaryOp.ADD);
    	BINARY_OPS.put("-", BinaryOp.SUBTRACT);
    	BINARY_OPS.put("*", BinaryOp.MULTIPLY);
    	BINARY_OPS.put("/", BinaryOp.DIVIDE);
    	BINARY_OPS.put("%", BinaryOp.REMAINDER);
    	BINARY_OPS.put("&", BinaryOp.AND);
    	BINARY_OPS.put("|", BinaryOp.OR);
    	BINARY_OPS.put("^", BinaryOp.XOR);
    	BINARY_OPS.put(">>", BinaryOp.SHIFT_RIGHT);
    	BINARY_OPS.put("<<", BinaryOp.SHIFT_LEFT);
    	BINARY_OPS.put(">>>", BinaryOp.UNSIGNED_SHIFT_RIGHT);
    	
    	UNARY_OPS.put("NEG", UnaryOp.NEGATE);
    	UNARY_OPS.put("~", UnaryOp.NOT);
    	
        COMPARISONS.put("==", Comparison.EQ);
        COMPARISONS.put("!=", Comparison.NE);
        COMPARISONS.put("<", Comparison.LT);
        COMPARISONS.put("<=", Comparison.LE);
        COMPARISONS.put(">", Comparison.GT);
        COMPARISONS.put(">=", Comparison.GE);
        
        COMPARISON_OPPOSITES.put(Comparison.EQ, Comparison.NE);
        COMPARISON_OPPOSITES.put(Comparison.NE, Comparison.EQ);
        COMPARISON_OPPOSITES.put(Comparison.LT, Comparison.GE);
        COMPARISON_OPPOSITES.put(Comparison.GE, Comparison.LT);
        COMPARISON_OPPOSITES.put(Comparison.GT, Comparison.LE);
        COMPARISON_OPPOSITES.put(Comparison.LE, Comparison.GT);        
    }
    
    static class InstructionContext {
    	public Set<String> locals = new HashSet<String>();
    	public Map<String, Label> labels = new HashMap<String, Label>();
    	public List<Instruction> instructions = new ArrayList<Instruction>();
    	
    	int syntheticLocalCounter = 0;
    	public String nextSyntheticLocal() {
    		syntheticLocalCounter++;
    		String name = "!" + syntheticLocalCounter;
    		locals.add(name);
    		return name;
    	}
    	public void resetSyntheticLocals() {
    		syntheticLocalCounter = 0;
    	}
    	
    	int labelCounter = 0;
    	public String nextLabel() {
    	    labelCounter++;
    	    String name = "L"+labelCounter;
    	    labels.put(name, new Label());
    	    return name;
    	}
    }
    
    static Tree createParseTree(String userScript) throws Exception {
    	userScript = userScript.trim() + "\n";
        ANTLRInputStream input = new ANTLRInputStream(new ByteArrayInputStream(userScript.getBytes("utf-8")));
        ScriptGrammarLexer lexer = new ScriptGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ScriptGrammarParser parser = new ScriptGrammarParser(tokens);
        ScriptGrammarParser.prog_return r = parser.prog();
        return (Tree)r.getTree();
    }
    
    static void getTreeDebugString(Tree tree, String prefix, StringBuilder sb) {
        String msg = prefix + tree.getText() + "\n";
        sb.append(msg);
        android.util.Log.d("DexCodeGenerator.tree", msg);
        for(int i=0; i<tree.getChildCount(); i++) {
            getTreeDebugString(tree.getChild(i), prefix+"  ", sb);
        }
    }
    
    static String treeDebugString(Tree tree) {
        StringBuilder sb = new StringBuilder();
        getTreeDebugString(tree, "", sb);
        return sb.toString();
    }
    
    public static InstructionContext createInstructionList(String userScript) throws Exception {
    	InstructionContext context = new InstructionContext();
    	// parse input
    	Tree tree = createParseTree(userScript);
    	if (DEBUG) {
    	    String ts = treeDebugString(tree);
    	    android.util.Log.d("DexCodeGenerator.tree", ts);
    	}
    	// generate instructions in memory
    	generateInstructions(tree, context);
    	return context;
    }
    
    public static void generateMethodCode(Code code, Map<String, Local> providedLocals, 
            TypeId thisType, InstructionContext context) throws Exception {
    	// create locals that aren't provided
    	Map<String, Local> allLocals = new HashMap(providedLocals);
    	for(String localName : context.locals) {
    		if (!allLocals.containsKey(localName)) {
    			// FIXME: need to support boolean at least, float would be nice
    			allLocals.put(localName, code.newLocal(TypeId.INT));
    		}
    	}
    	// write code now that we have all the locals available
    	for(Instruction inst : context.instructions) {
    	    if (DEBUG) {
                android.util.Log.i("DexCodeGenerator", "Generating instruction: " + inst);
    	    }
    		inst.generateCode(code, allLocals, context.labels, thisType);
    	}
    }
    
    public static void generateMethodCode(Code code, Map<String, Local> providedLocals, TypeId thisType, String userScript) throws Exception {
        InstructionContext context = createInstructionList(userScript);
        generateMethodCode(code, providedLocals, thisType, context);
    }
	
    static abstract class Instruction {
    	abstract void generateCode(Code code, Map<String, Local> localMap, Map<String, Label> labelMap, TypeId thisType);
    	
    	public boolean equals(Object other) {
    		return (other!=null && this.getClass()==other.getClass() && this.toString().equals(other.toString()));
    	}
    	
    	public int hashCode() {
    		return this.toString().hashCode();
    	}
    }
    
    static class ConstantIntAssignment extends Instruction {
		public final String targetLocal;
    	public final int value;
    	public ConstantIntAssignment(String targetLocal, int value) {
			this.targetLocal = targetLocal;
			this.value = value;
		}

    	public void generateCode(Code code, Map<String, Local> localMap, Map<String, Label> labelMap, TypeId thisType) {
    		code.loadConstant(localMap.get(targetLocal), value);
    	}
    	public String toString() {
    		return String.format("[%s] <- %s", targetLocal, value);
    	}
    }
    
    static class IntAssignment extends Instruction {
    	public final String targetLocal;
    	public final String sourceLocal;
    	public IntAssignment(String targetLocal, String sourceLocal) {
			this.targetLocal = targetLocal;
			this.sourceLocal = sourceLocal;
		}
    	
		public void generateCode(Code code, Map<String, Local> localMap, Map<String, Label> labelMap, TypeId thisType) {
    		code.move(localMap.get(targetLocal), localMap.get(sourceLocal));
    	}
    	public String toString() {
    		return String.format("[%s] <- [%s]", targetLocal, sourceLocal);
    	}
    }
    
    static class BinaryIntOperation extends Instruction {
    	public final BinaryOp operator;
    	public final String leftLocal;
    	public final String rightLocal;
    	public final String targetLocal;
    	public BinaryIntOperation(String targetLocal, BinaryOp operator, String leftLocal, String rightLocal) {
			this.operator = operator;
			this.leftLocal = leftLocal;
			this.rightLocal = rightLocal;
			this.targetLocal = targetLocal;
		}
    	
		public void generateCode(Code code, Map<String, Local> localMap, Map<String, Label> labelMap, TypeId thisType) {
    		code.op(operator, localMap.get(targetLocal), localMap.get(leftLocal), localMap.get(rightLocal));
    	}
    	public String toString() {
    		return String.format("[%s] <- %s([%s], [%s])", targetLocal, operator, leftLocal, rightLocal);
    	}
    }
    
    static class UnaryIntOperation extends Instruction {
    	public final UnaryOp operator;
    	public final String sourceLocal;
    	public final String targetLocal;
    	public UnaryIntOperation(String targetLocal, UnaryOp operator, String sourceLocal) {
			this.operator = operator;
			this.sourceLocal = sourceLocal;
			this.targetLocal = targetLocal;
		}
    	
		public void generateCode(Code code, Map<String, Local> localMap, Map<String, Label> labelMap, TypeId thisType) {
    		code.op(operator, localMap.get(targetLocal), localMap.get(sourceLocal));
    	}
    	public String toString() {
    		return String.format("[%s] <- %s([%s])", targetLocal, operator, sourceLocal);
    	}
    }
    
    static class FunctionCallInstruction extends Instruction {
    	public final String functionName;
    	public final String[] argumentLocals;
    	public final String targetLocal;
    	public FunctionCallInstruction(String targetLocal, String functionName, String[] argumentLocals) {
			this.functionName = functionName;
			this.argumentLocals = argumentLocals;
			this.targetLocal = targetLocal;
		}

		public void generateCode(Code code, Map<String, Local> localMap, Map<String, Label> labelMap, TypeId thisType) {
    		// call the superclass (DexImageScript) method with the script_ prefix
    		TypeId superclass = TypeId.get(DexImageScript.class);
    		String methodName = "script_" + functionName;
    		// get method parametesr
    		TypeId[] parameterTypes = null;
    		try {
    			Method[] declaredMethods = DexImageScript.class.getDeclaredMethods();
    			for(Method m : declaredMethods) {
    				if (methodName.equals(m.getName())) {
    					Class[] paramClasses = m.getParameterTypes();
    					if (paramClasses.length==this.argumentLocals.length) {
                            parameterTypes = new TypeId[paramClasses.length];
                            for(int i=0; i<paramClasses.length; i++) {
                                parameterTypes[i] = TypeId.get(paramClasses[i]);
                            }
    					}
    				}
    			}
    		}
    		catch(Exception ex) {
    			throw new IllegalStateException(ex);
    		}
    		if (parameterTypes==null) {
    		    throw new IllegalStateException("Function " + functionName + " not found");
    		}
    		MethodId methodId = superclass.getMethod(TypeId.INT, methodName, parameterTypes);
    		Local[] parameterLocals = new Local[argumentLocals.length];
    		for(int i=0; i<argumentLocals.length; i++) {
    			parameterLocals[i] = localMap.get(argumentLocals[i]);
    		}
    		code.invokeSuper(methodId, localMap.get(targetLocal), code.getThis(thisType), parameterLocals);
    	}

    	public String toString() {
    		StringBuilder argString = new StringBuilder();
    		if (argumentLocals!=null) {
    			if (argumentLocals.length>0) argString.append(String.format("[%s]", argumentLocals[0]));
    			for(int i=1; i<argumentLocals.length; i++) {
    				argString.append(String.format(", [%s]", argumentLocals[i]));
    			}
    		}
    		return String.format("[%s] <- CALL %s(%s)", targetLocal, functionName, argString.toString());
    	}
    }
    
    static class ReturnInstruction extends Instruction {
    	public final String targetLocal;
    	public ReturnInstruction(String targetLocal) {
			this.targetLocal = targetLocal;
		}
    	
		public void generateCode(Code code, Map<String, Local> localMap, Map<String, Label> labelMap, TypeId thisType) {
    		code.returnValue(localMap.get(targetLocal));
    	}
    	public String toString() {
    		return String.format("RETURN [%s]", targetLocal);
    	}
    }
    
    static class CompareInstruction extends Instruction {
        public final String leftLocal;
        public final String rightLocal;
        public final Comparison compareOp;
        public final String trueLabel;
        public CompareInstruction(String leftLocal, Comparison compareOp, String rightLocal, String trueLabel) {
            this.leftLocal = leftLocal;
            this.rightLocal = rightLocal;
            this.compareOp = compareOp;
            this.trueLabel = trueLabel;
        }
        
        public void generateCode(Code code, Map<String, Local> localMap, Map<String, Label> labelMap, TypeId thisType) {
            code.compare(compareOp, labelMap.get(trueLabel), localMap.get(leftLocal), localMap.get(rightLocal));
        }
        
        public String toString() {
            return String.format("IF [%s] %s [%s] JUMP %s", leftLocal, compareOp, rightLocal, trueLabel);
        }
    }
    
    static class LabelInstruction extends Instruction {
        public final String labelName;
        public LabelInstruction(String labelName) {
            this.labelName = labelName;
        }
        
        public void generateCode(Code code, Map<String, Local> localMap, Map<String, Label> labelMap, TypeId thisType) {
            code.mark(labelMap.get(labelName));
        }
        
        public String toString() {
            return String.format("LABEL %s", labelName);
        }        
    }
    
    static class JumpInstruction extends Instruction {
        public final String labelName;
        public JumpInstruction(String labelName) {
            this.labelName = labelName;
        }
        
        public void generateCode(Code code, Map<String, Local> localMap, Map<String, Label> labelMap, TypeId thisType) {
            code.jump(labelMap.get(labelName));
        }
        
        public String toString() {
            return String.format("JUMP %s", labelName);
        }        
    }
    
    static class ReturnVoidInstruction extends Instruction {
        public void generateCode(Code code, Map<String, Local> localMap, Map<String, Label> labelMap, TypeId thisType) {
            code.returnVoid();
        }
        
        public String toString() {
            return "RETURN";
        }        
    }
    
    static void generateInstructions(Tree root, InstructionContext context) {
    	if (root.getText()!=null) {
    		// this is a single instruction instead of a list of instructions
            generateInstructionsForSubtree(root, context);
            context.resetSyntheticLocals();
    	}
    	else {
        	int size = root.getChildCount();
        	for(int i=0; i<size; i++) {
        		generateInstructionsForSubtree(root.getChild(i), context);
        		context.resetSyntheticLocals();
        	}
    	}
    }
    
    static String generateInstructionsForSubtree(Tree tree, InstructionContext context) {
    	return generateInstructionsForSubtree(tree, context, null);
    }
    
    // TODO: refactor
    static String generateInstructionsForSubtree(Tree tree, InstructionContext context, String targetName) {
    	String token = tree.getText();
    	if (tree.getChildCount()==0) {
    		// this is a literal or a variable name
    		return resolveLocal(token, context, targetName);
    	}
    	else if (BINARY_OPS.containsKey(token)) {
    		// create synthetic local to store result if needed
    		String leftArg = generateInstructionsForSubtree(tree.getChild(0), context);
    		String rightArg = generateInstructionsForSubtree(tree.getChild(1), context);
    		String target = (targetName!=null) ? targetName : context.nextSyntheticLocal();
    		BinaryIntOperation inst = new BinaryIntOperation(target, BINARY_OPS.get(token), leftArg, rightArg);
    		context.instructions.add(inst);
    		return target;
    	}
    	else if (UNARY_OPS.containsKey(token)) {
    		// TODO: if argument is a constant, modify just-generated load instruction to store computed value directly
    		String arg = generateInstructionsForSubtree(tree.getChild(0), context);
    		String target = (targetName!=null) ? targetName : context.nextSyntheticLocal();
    		UnaryIntOperation inst = new UnaryIntOperation(target, UNARY_OPS.get(token), arg);
    		context.instructions.add(inst);
    		return target;
    	}
    	else if ("=".equals(token)) {
    		// TODO: verify that target can be assigned to
    		String target = tree.getChild(0).getText();
            // We need to add the target to the set of locals, in case it's not referenced in an expression.
            // (In which case this instruction is likely useless).
            context.locals.add(target);
    		// passing target to the recursive call will cause its result to be assigned to target
    		generateInstructionsForSubtree(tree.getChild(1), context, target);
    		return target;
    	}
    	else if ("CALL".equals(token)) {
    		int tsize = tree.getChildCount();
    		String functionName = tree.getChild(0).getText();
    		String[] arguments = new String[tsize-1];
    		for(int i=1; i<tsize; i++) {
    			arguments[i-1] = generateInstructionsForSubtree(tree.getChild(i), context);
    		}
    		String target = (targetName!=null) ? targetName : context.nextSyntheticLocal();
    		FunctionCallInstruction inst = new FunctionCallInstruction(target, functionName, arguments);
    		context.instructions.add(inst);
    		return inst.targetLocal;
    	}
    	else if ("return".equals(token)) {
    		String result = generateInstructionsForSubtree(tree.getChild(0), context);
    		ReturnInstruction inst = new ReturnInstruction(result);
    		context.instructions.add(inst);
    		return ""; // shouldn't be used because this should be a top-level statement
    	}
        else if ("BLOCK".equals(token)) {
            for (int ii=0; ii<tree.getChildCount(); ii++) {
                generateInstructions(tree.getChild(ii), context);
            }
            return "";
        }
        else if ("if".equals(token)) {
            if (tree.getChildCount()==3) {
                // if and else subtrees
                String elseLabel = context.nextLabel();
                String endLabel = context.nextLabel();
                // jump to else label if condition is false
                generateInstructionsForBooleanExpression(tree.getChild(0), elseLabel, context);
                generateInstructions(tree.getChild(1), context);
                // jump over else block to end, unless last statement was return
                // (after a return statement a label must be added before further instructions)
                if (!(context.instructions.get(context.instructions.size()-1) instanceof ReturnInstruction)) {
                    context.instructions.add(new JumpInstruction(endLabel));
                }
                // code for else block
                context.instructions.add(new LabelInstruction(elseLabel));
                generateInstructions(tree.getChild(2), context);
                context.instructions.add(new LabelInstruction(endLabel));
            }
            else {
                // no else, jump to label past block if condition is false
                String endLabel = context.nextLabel();
                generateInstructionsForBooleanExpression(tree.getChild(0), endLabel, context);
                generateInstructions(tree.getChild(1), context);
                context.instructions.add(new LabelInstruction(endLabel));
            }
        }
        else if ("while".equals(token)) {
            // put label before test, if condition is false jump to exit, at end of loop jump to top
            String topLabelName = context.nextLabel();
            String exitLabelName = context.nextLabel();
            context.instructions.add(new LabelInstruction(topLabelName));
            generateInstructionsForBooleanExpression(tree.getChild(0), exitLabelName, context);
            // loop body, then jump back to top
            generateInstructions(tree.getChild(1), context);
            context.instructions.add(new JumpInstruction(topLabelName));
            // exit label
            context.instructions.add(new LabelInstruction(exitLabelName));
        }
        else if ("for".equals(token)) {
            int numChildren = tree.getChildCount();
            if (numChildren<3 || numChildren>5) {
                throw new IllegalStateException("'for' with invalid number of children:" + numChildren);
            }
            // "for i,10 [block]" is equivalent to "i=0; while i<10 {[block]; i=i+1}"
            String loopIndexLocal = tree.getChild(0).getText(); // must be variable
            context.locals.add(loopIndexLocal);
            String tmpLocal = context.nextSyntheticLocal();
            String topLabelName = context.nextLabel();
            String exitLabelName = context.nextLabel();
            
            // variants: "for i,start,end [block]", "for i,start,end,step [block]"
            // start defaults to 0, step to 1
            String loopStartExpr = (numChildren>=4) ? tree.getChild(1).getText() : "0";
            String loopEndExpr = (numChildren>=4) ? tree.getChild(2).getText() : tree.getChild(1).getText();
            String stepExpr = (numChildren>=5) ? tree.getChild(3).getText() : "1";
            
            // set loop index to start
            resolveLocal(loopStartExpr, context, loopIndexLocal);
            // top label
            context.instructions.add(new LabelInstruction(topLabelName));
            // jump to end if loop limit reached
            resolveLocal(loopEndExpr, context, tmpLocal);
            context.instructions.add(new CompareInstruction(loopIndexLocal, Comparison.GE, tmpLocal, exitLabelName));
            // loop body
            generateInstructions(tree.getChild(numChildren-1), context);
            // increment loop index, then jump back to top
            resolveLocal(stepExpr, context, tmpLocal);
            context.instructions.add(new BinaryIntOperation(loopIndexLocal, BinaryOp.ADD, loopIndexLocal, tmpLocal));
            context.instructions.add(new JumpInstruction(topLabelName));
            // exit label
            context.instructions.add(new LabelInstruction(exitLabelName));
        }
        else {
            android.util.Log.w("DexCodeGenerator", "Unknown token: " + token);
        }
    	return "";
    }
    
    static void generateInstructionsForBooleanExpression(Tree tree, String labelName, InstructionContext context) {
        // for now, limited to atom [comp] atom
        String leftLocal = resolveLocal(tree.getChild(0).getText(), context, null);
        String rightLocal = resolveLocal(tree.getChild(1).getText(), context, null);
        // Because Code.compare branches on true, we want to invert the comparison so that
        // we continue execution if the condition is true and jump if the condition is false.
        String token = tree.getText();
        Comparison compareOp = COMPARISON_OPPOSITES.get(COMPARISONS.get(token));
        context.instructions.add(new CompareInstruction(leftLocal, compareOp, rightLocal, labelName));
    }
    
    static String resolveLocal(String text, InstructionContext context, String targetName) {
    	// number?
    	try {
    		int value = Integer.parseInt(text);
    		// create local and add assignment instruction
    		String constLocal = (targetName!=null) ? targetName : context.nextSyntheticLocal();
    		ConstantIntAssignment inst = new ConstantIntAssignment(constLocal, value);
    		context.instructions.add(inst);
    		return constLocal;
    	}
    	catch(Exception ignored) {}
    	// variable name; assign if we have a target
    	context.locals.add(text);
    	if (targetName!=null) {
    		IntAssignment inst = new IntAssignment(targetName, text);
    		context.instructions.add(inst);
    	}
    	return text;
    }

}
