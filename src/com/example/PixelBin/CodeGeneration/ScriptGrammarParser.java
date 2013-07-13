// $ANTLR 3.4 ScriptGrammar.g 2012-05-20 13:30:06

package com.example.PixelBin.CodeGeneration;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class ScriptGrammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BLOCK", "CALL", "ID", "INT", "NEG", "NEWLINE", "WS", "'!='", "'%'", "'&'", "'('", "')'", "'*'", "'+'", "','", "'-'", "'/'", "'<'", "'<<'", "'<<<'", "'<='", "'='", "'=='", "'>'", "'>='", "'>>'", "'else'", "'for'", "'if'", "'return'", "'while'", "'{'", "'|'", "'}'", "'~'"
    };

    public static final int EOF=-1;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int BLOCK=4;
    public static final int CALL=5;
    public static final int ID=6;
    public static final int INT=7;
    public static final int NEG=8;
    public static final int NEWLINE=9;
    public static final int WS=10;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public ScriptGrammarParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public ScriptGrammarParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return ScriptGrammarParser.tokenNames; }
    public String getGrammarFileName() { return "ScriptGrammar.g"; }


    public static class prog_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "prog"
    // ScriptGrammar.g:24:1: prog : ( block )+ ;
    public final prog_return prog() throws RecognitionException {
        prog_return retval = new prog_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        block_return block1 =null;



        try {
            // ScriptGrammar.g:24:5: ( ( block )+ )
            // ScriptGrammar.g:24:9: ( block )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // ScriptGrammar.g:24:9: ( block )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= ID && LA1_0 <= INT)||LA1_0==NEWLINE||LA1_0==14||LA1_0==19||(LA1_0 >= 31 && LA1_0 <= 35)||LA1_0==38) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ScriptGrammar.g:24:10: block
            	    {
            	    pushFollow(FOLLOW_block_in_prog91);
            	    block1=block();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, block1.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "prog"


    public static class block_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "block"
    // ScriptGrammar.g:26:1: block : ( '{' NEWLINE ( block )* '}' -> ^( BLOCK ( block )* ) | ( stat NEWLINE ) -> stat | NEWLINE ->);
    public final block_return block() throws RecognitionException {
        block_return retval = new block_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal2=null;
        Token NEWLINE3=null;
        Token char_literal5=null;
        Token NEWLINE7=null;
        Token NEWLINE8=null;
        block_return block4 =null;

        stat_return stat6 =null;


        CommonTree char_literal2_tree=null;
        CommonTree NEWLINE3_tree=null;
        CommonTree char_literal5_tree=null;
        CommonTree NEWLINE7_tree=null;
        CommonTree NEWLINE8_tree=null;
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_35=new RewriteRuleTokenStream(adaptor,"token 35");
        RewriteRuleTokenStream stream_37=new RewriteRuleTokenStream(adaptor,"token 37");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_stat=new RewriteRuleSubtreeStream(adaptor,"rule stat");
        try {
            // ScriptGrammar.g:26:6: ( '{' NEWLINE ( block )* '}' -> ^( BLOCK ( block )* ) | ( stat NEWLINE ) -> stat | NEWLINE ->)
            int alt3=3;
            switch ( input.LA(1) ) {
            case 35:
                {
                alt3=1;
                }
                break;
            case ID:
            case INT:
            case 14:
            case 19:
            case 31:
            case 32:
            case 33:
            case 34:
            case 38:
                {
                alt3=2;
                }
                break;
            case NEWLINE:
                {
                alt3=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // ScriptGrammar.g:26:8: '{' NEWLINE ( block )* '}'
                    {
                    char_literal2=(Token)match(input,35,FOLLOW_35_in_block101); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_35.add(char_literal2);


                    NEWLINE3=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_block103); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE3);


                    // ScriptGrammar.g:26:20: ( block )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( ((LA2_0 >= ID && LA2_0 <= INT)||LA2_0==NEWLINE||LA2_0==14||LA2_0==19||(LA2_0 >= 31 && LA2_0 <= 35)||LA2_0==38) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // ScriptGrammar.g:26:20: block
                    	    {
                    	    pushFollow(FOLLOW_block_in_block105);
                    	    block4=block();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_block.add(block4.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    char_literal5=(Token)match(input,37,FOLLOW_37_in_block108); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_37.add(char_literal5);


                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 26:31: -> ^( BLOCK ( block )* )
                    {
                        // ScriptGrammar.g:26:34: ^( BLOCK ( block )* )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(BLOCK, "BLOCK")
                        , root_1);

                        // ScriptGrammar.g:26:42: ( block )*
                        while ( stream_block.hasNext() ) {
                            adaptor.addChild(root_1, stream_block.nextTree());

                        }
                        stream_block.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // ScriptGrammar.g:27:8: ( stat NEWLINE )
                    {
                    // ScriptGrammar.g:27:8: ( stat NEWLINE )
                    // ScriptGrammar.g:27:9: stat NEWLINE
                    {
                    pushFollow(FOLLOW_stat_in_block127);
                    stat6=stat();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_stat.add(stat6.getTree());

                    NEWLINE7=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_block129); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE7);


                    }


                    // AST REWRITE
                    // elements: stat
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 27:23: -> stat
                    {
                        adaptor.addChild(root_0, stream_stat.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // ScriptGrammar.g:28:8: NEWLINE
                    {
                    NEWLINE8=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_block143); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE8);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 28:16: ->
                    {
                        root_0 = null;
                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "block"


    public static class statblock_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "statblock"
    // ScriptGrammar.g:33:1: statblock : ( ( '{' )=> block | stat );
    public final statblock_return statblock() throws RecognitionException {
        statblock_return retval = new statblock_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        block_return block9 =null;

        stat_return stat10 =null;



        try {
            // ScriptGrammar.g:33:10: ( ( '{' )=> block | stat )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==35) && (synpred1_ScriptGrammar())) {
                alt4=1;
            }
            else if ( (LA4_0==19) ) {
                int LA4_2 = input.LA(2);

                if ( (synpred1_ScriptGrammar()) ) {
                    alt4=1;
                }
                else if ( (true) ) {
                    alt4=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    throw nvae;

                }
            }
            else if ( (LA4_0==38) ) {
                int LA4_3 = input.LA(2);

                if ( (synpred1_ScriptGrammar()) ) {
                    alt4=1;
                }
                else if ( (true) ) {
                    alt4=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA4_0==INT) ) {
                int LA4_4 = input.LA(2);

                if ( (synpred1_ScriptGrammar()) ) {
                    alt4=1;
                }
                else if ( (true) ) {
                    alt4=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 4, input);

                    throw nvae;

                }
            }
            else if ( (LA4_0==ID) ) {
                int LA4_5 = input.LA(2);

                if ( (synpred1_ScriptGrammar()) ) {
                    alt4=1;
                }
                else if ( (true) ) {
                    alt4=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 5, input);

                    throw nvae;

                }
            }
            else if ( (LA4_0==14) ) {
                int LA4_6 = input.LA(2);

                if ( (synpred1_ScriptGrammar()) ) {
                    alt4=1;
                }
                else if ( (true) ) {
                    alt4=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 6, input);

                    throw nvae;

                }
            }
            else if ( (LA4_0==33) ) {
                int LA4_7 = input.LA(2);

                if ( (synpred1_ScriptGrammar()) ) {
                    alt4=1;
                }
                else if ( (true) ) {
                    alt4=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 7, input);

                    throw nvae;

                }
            }
            else if ( (LA4_0==32) ) {
                int LA4_8 = input.LA(2);

                if ( (synpred1_ScriptGrammar()) ) {
                    alt4=1;
                }
                else if ( (true) ) {
                    alt4=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 8, input);

                    throw nvae;

                }
            }
            else if ( (LA4_0==34) ) {
                int LA4_9 = input.LA(2);

                if ( (synpred1_ScriptGrammar()) ) {
                    alt4=1;
                }
                else if ( (true) ) {
                    alt4=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 9, input);

                    throw nvae;

                }
            }
            else if ( (LA4_0==31) ) {
                int LA4_10 = input.LA(2);

                if ( (synpred1_ScriptGrammar()) ) {
                    alt4=1;
                }
                else if ( (true) ) {
                    alt4=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 10, input);

                    throw nvae;

                }
            }
            else if ( (LA4_0==NEWLINE) && (synpred1_ScriptGrammar())) {
                alt4=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // ScriptGrammar.g:33:12: ( '{' )=> block
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_block_in_statblock165);
                    block9=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, block9.getTree());

                    }
                    break;
                case 2 :
                    // ScriptGrammar.g:34:12: stat
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_stat_in_statblock178);
                    stat10=stat();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, stat10.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "statblock"


    public static class stat_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stat"
    // ScriptGrammar.g:37:1: stat : ( expr | assign | retexp | ifexp | whileexp | forexp );
    public final stat_return stat() throws RecognitionException {
        stat_return retval = new stat_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        expr_return expr11 =null;

        assign_return assign12 =null;

        retexp_return retexp13 =null;

        ifexp_return ifexp14 =null;

        whileexp_return whileexp15 =null;

        forexp_return forexp16 =null;



        try {
            // ScriptGrammar.g:37:5: ( expr | assign | retexp | ifexp | whileexp | forexp )
            int alt5=6;
            switch ( input.LA(1) ) {
            case INT:
            case 14:
            case 19:
            case 38:
                {
                alt5=1;
                }
                break;
            case ID:
                {
                int LA5_2 = input.LA(2);

                if ( (LA5_2==EOF||LA5_2==NEWLINE||(LA5_2 >= 12 && LA5_2 <= 14)||(LA5_2 >= 16 && LA5_2 <= 17)||(LA5_2 >= 19 && LA5_2 <= 20)||(LA5_2 >= 22 && LA5_2 <= 23)||(LA5_2 >= 29 && LA5_2 <= 30)||LA5_2==36) ) {
                    alt5=1;
                }
                else if ( (LA5_2==25) ) {
                    alt5=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 2, input);

                    throw nvae;

                }
                }
                break;
            case 33:
                {
                alt5=3;
                }
                break;
            case 32:
                {
                alt5=4;
                }
                break;
            case 34:
                {
                alt5=5;
                }
                break;
            case 31:
                {
                alt5=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // ScriptGrammar.g:37:9: expr
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_expr_in_stat192);
                    expr11=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr11.getTree());

                    }
                    break;
                case 2 :
                    // ScriptGrammar.g:38:9: assign
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_assign_in_stat202);
                    assign12=assign();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assign12.getTree());

                    }
                    break;
                case 3 :
                    // ScriptGrammar.g:39:9: retexp
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_retexp_in_stat212);
                    retexp13=retexp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, retexp13.getTree());

                    }
                    break;
                case 4 :
                    // ScriptGrammar.g:40:9: ifexp
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_ifexp_in_stat222);
                    ifexp14=ifexp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ifexp14.getTree());

                    }
                    break;
                case 5 :
                    // ScriptGrammar.g:41:9: whileexp
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_whileexp_in_stat232);
                    whileexp15=whileexp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, whileexp15.getTree());

                    }
                    break;
                case 6 :
                    // ScriptGrammar.g:42:9: forexp
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_forexp_in_stat242);
                    forexp16=forexp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, forexp16.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stat"


    public static class assign_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assign"
    // ScriptGrammar.g:45:1: assign : ID '=' expr -> ^( '=' ID expr ) ;
    public final assign_return assign() throws RecognitionException {
        assign_return retval = new assign_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID17=null;
        Token char_literal18=null;
        expr_return expr19 =null;


        CommonTree ID17_tree=null;
        CommonTree char_literal18_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_25=new RewriteRuleTokenStream(adaptor,"token 25");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // ScriptGrammar.g:45:7: ( ID '=' expr -> ^( '=' ID expr ) )
            // ScriptGrammar.g:45:9: ID '=' expr
            {
            ID17=(Token)match(input,ID,FOLLOW_ID_in_assign258); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID17);


            char_literal18=(Token)match(input,25,FOLLOW_25_in_assign260); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_25.add(char_literal18);


            pushFollow(FOLLOW_expr_in_assign262);
            expr19=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr19.getTree());

            // AST REWRITE
            // elements: ID, 25, expr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 45:21: -> ^( '=' ID expr )
            {
                // ScriptGrammar.g:45:24: ^( '=' ID expr )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_25.nextNode()
                , root_1);

                adaptor.addChild(root_1, 
                stream_ID.nextNode()
                );

                adaptor.addChild(root_1, stream_expr.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assign"


    public static class retexp_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "retexp"
    // ScriptGrammar.g:48:1: retexp : 'return' expr -> ^( 'return' expr ) ;
    public final retexp_return retexp() throws RecognitionException {
        retexp_return retval = new retexp_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal20=null;
        expr_return expr21 =null;


        CommonTree string_literal20_tree=null;
        RewriteRuleTokenStream stream_33=new RewriteRuleTokenStream(adaptor,"token 33");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // ScriptGrammar.g:48:7: ( 'return' expr -> ^( 'return' expr ) )
            // ScriptGrammar.g:48:9: 'return' expr
            {
            string_literal20=(Token)match(input,33,FOLLOW_33_in_retexp288); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_33.add(string_literal20);


            pushFollow(FOLLOW_expr_in_retexp290);
            expr21=expr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expr.add(expr21.getTree());

            // AST REWRITE
            // elements: 33, expr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 48:23: -> ^( 'return' expr )
            {
                // ScriptGrammar.g:48:26: ^( 'return' expr )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_33.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_expr.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "retexp"


    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr"
    // ScriptGrammar.g:51:1: expr : multExpr ( ( '+' ^| '-' ^| '&' ^| '|' ^| '>>' ^| '<<' ^| '<<<' ^) multExpr )* ;
    public final expr_return expr() throws RecognitionException {
        expr_return retval = new expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal23=null;
        Token char_literal24=null;
        Token char_literal25=null;
        Token char_literal26=null;
        Token string_literal27=null;
        Token string_literal28=null;
        Token string_literal29=null;
        multExpr_return multExpr22 =null;

        multExpr_return multExpr30 =null;


        CommonTree char_literal23_tree=null;
        CommonTree char_literal24_tree=null;
        CommonTree char_literal25_tree=null;
        CommonTree char_literal26_tree=null;
        CommonTree string_literal27_tree=null;
        CommonTree string_literal28_tree=null;
        CommonTree string_literal29_tree=null;

        try {
            // ScriptGrammar.g:51:5: ( multExpr ( ( '+' ^| '-' ^| '&' ^| '|' ^| '>>' ^| '<<' ^| '<<<' ^) multExpr )* )
            // ScriptGrammar.g:51:9: multExpr ( ( '+' ^| '-' ^| '&' ^| '|' ^| '>>' ^| '<<' ^| '<<<' ^) multExpr )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_multExpr_in_expr316);
            multExpr22=multExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, multExpr22.getTree());

            // ScriptGrammar.g:51:18: ( ( '+' ^| '-' ^| '&' ^| '|' ^| '>>' ^| '<<' ^| '<<<' ^) multExpr )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==13||LA7_0==17||LA7_0==19||(LA7_0 >= 22 && LA7_0 <= 23)||LA7_0==29||LA7_0==36) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ScriptGrammar.g:51:19: ( '+' ^| '-' ^| '&' ^| '|' ^| '>>' ^| '<<' ^| '<<<' ^) multExpr
            	    {
            	    // ScriptGrammar.g:51:19: ( '+' ^| '-' ^| '&' ^| '|' ^| '>>' ^| '<<' ^| '<<<' ^)
            	    int alt6=7;
            	    switch ( input.LA(1) ) {
            	    case 17:
            	        {
            	        alt6=1;
            	        }
            	        break;
            	    case 19:
            	        {
            	        alt6=2;
            	        }
            	        break;
            	    case 13:
            	        {
            	        alt6=3;
            	        }
            	        break;
            	    case 36:
            	        {
            	        alt6=4;
            	        }
            	        break;
            	    case 29:
            	        {
            	        alt6=5;
            	        }
            	        break;
            	    case 22:
            	        {
            	        alt6=6;
            	        }
            	        break;
            	    case 23:
            	        {
            	        alt6=7;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 6, 0, input);

            	        throw nvae;

            	    }

            	    switch (alt6) {
            	        case 1 :
            	            // ScriptGrammar.g:51:20: '+' ^
            	            {
            	            char_literal23=(Token)match(input,17,FOLLOW_17_in_expr320); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal23_tree = 
            	            (CommonTree)adaptor.create(char_literal23)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal23_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // ScriptGrammar.g:51:25: '-' ^
            	            {
            	            char_literal24=(Token)match(input,19,FOLLOW_19_in_expr323); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal24_tree = 
            	            (CommonTree)adaptor.create(char_literal24)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal24_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 3 :
            	            // ScriptGrammar.g:51:30: '&' ^
            	            {
            	            char_literal25=(Token)match(input,13,FOLLOW_13_in_expr326); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal25_tree = 
            	            (CommonTree)adaptor.create(char_literal25)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal25_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 4 :
            	            // ScriptGrammar.g:51:35: '|' ^
            	            {
            	            char_literal26=(Token)match(input,36,FOLLOW_36_in_expr329); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal26_tree = 
            	            (CommonTree)adaptor.create(char_literal26)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal26_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 5 :
            	            // ScriptGrammar.g:51:40: '>>' ^
            	            {
            	            string_literal27=(Token)match(input,29,FOLLOW_29_in_expr332); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            string_literal27_tree = 
            	            (CommonTree)adaptor.create(string_literal27)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(string_literal27_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 6 :
            	            // ScriptGrammar.g:51:46: '<<' ^
            	            {
            	            string_literal28=(Token)match(input,22,FOLLOW_22_in_expr335); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            string_literal28_tree = 
            	            (CommonTree)adaptor.create(string_literal28)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(string_literal28_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 7 :
            	            // ScriptGrammar.g:51:52: '<<<' ^
            	            {
            	            string_literal29=(Token)match(input,23,FOLLOW_23_in_expr338); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            string_literal29_tree = 
            	            (CommonTree)adaptor.create(string_literal29)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(string_literal29_tree, root_0);
            	            }

            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_multExpr_in_expr342);
            	    multExpr30=multExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, multExpr30.getTree());

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr"


    public static class multExpr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "multExpr"
    // ScriptGrammar.g:54:1: multExpr : unaryExp ( ( '*' ^| '/' ^| '%' ^) unaryExp )* ;
    public final multExpr_return multExpr() throws RecognitionException {
        multExpr_return retval = new multExpr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal32=null;
        Token char_literal33=null;
        Token char_literal34=null;
        unaryExp_return unaryExp31 =null;

        unaryExp_return unaryExp35 =null;


        CommonTree char_literal32_tree=null;
        CommonTree char_literal33_tree=null;
        CommonTree char_literal34_tree=null;

        try {
            // ScriptGrammar.g:55:5: ( unaryExp ( ( '*' ^| '/' ^| '%' ^) unaryExp )* )
            // ScriptGrammar.g:55:9: unaryExp ( ( '*' ^| '/' ^| '%' ^) unaryExp )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_unaryExp_in_multExpr364);
            unaryExp31=unaryExp();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExp31.getTree());

            // ScriptGrammar.g:55:18: ( ( '*' ^| '/' ^| '%' ^) unaryExp )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==12||LA9_0==16||LA9_0==20) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ScriptGrammar.g:55:19: ( '*' ^| '/' ^| '%' ^) unaryExp
            	    {
            	    // ScriptGrammar.g:55:19: ( '*' ^| '/' ^| '%' ^)
            	    int alt8=3;
            	    switch ( input.LA(1) ) {
            	    case 16:
            	        {
            	        alt8=1;
            	        }
            	        break;
            	    case 20:
            	        {
            	        alt8=2;
            	        }
            	        break;
            	    case 12:
            	        {
            	        alt8=3;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 8, 0, input);

            	        throw nvae;

            	    }

            	    switch (alt8) {
            	        case 1 :
            	            // ScriptGrammar.g:55:20: '*' ^
            	            {
            	            char_literal32=(Token)match(input,16,FOLLOW_16_in_multExpr368); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal32_tree = 
            	            (CommonTree)adaptor.create(char_literal32)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal32_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // ScriptGrammar.g:55:25: '/' ^
            	            {
            	            char_literal33=(Token)match(input,20,FOLLOW_20_in_multExpr371); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal33_tree = 
            	            (CommonTree)adaptor.create(char_literal33)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal33_tree, root_0);
            	            }

            	            }
            	            break;
            	        case 3 :
            	            // ScriptGrammar.g:55:30: '%' ^
            	            {
            	            char_literal34=(Token)match(input,12,FOLLOW_12_in_multExpr374); if (state.failed) return retval;
            	            if ( state.backtracking==0 ) {
            	            char_literal34_tree = 
            	            (CommonTree)adaptor.create(char_literal34)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(char_literal34_tree, root_0);
            	            }

            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_unaryExp_in_multExpr378);
            	    unaryExp35=unaryExp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExp35.getTree());

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "multExpr"


    public static class unaryExp_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unaryExp"
    // ScriptGrammar.g:58:1: unaryExp : ( '-' atom -> ^( NEG atom ) | '~' atom -> ^( '~' atom ) | atom );
    public final unaryExp_return unaryExp() throws RecognitionException {
        unaryExp_return retval = new unaryExp_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token char_literal36=null;
        Token char_literal38=null;
        atom_return atom37 =null;

        atom_return atom39 =null;

        atom_return atom40 =null;


        CommonTree char_literal36_tree=null;
        CommonTree char_literal38_tree=null;
        RewriteRuleTokenStream stream_19=new RewriteRuleTokenStream(adaptor,"token 19");
        RewriteRuleTokenStream stream_38=new RewriteRuleTokenStream(adaptor,"token 38");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        try {
            // ScriptGrammar.g:59:5: ( '-' atom -> ^( NEG atom ) | '~' atom -> ^( '~' atom ) | atom )
            int alt10=3;
            switch ( input.LA(1) ) {
            case 19:
                {
                alt10=1;
                }
                break;
            case 38:
                {
                alt10=2;
                }
                break;
            case ID:
            case INT:
            case 14:
                {
                alt10=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }

            switch (alt10) {
                case 1 :
                    // ScriptGrammar.g:59:9: '-' atom
                    {
                    char_literal36=(Token)match(input,19,FOLLOW_19_in_unaryExp404); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_19.add(char_literal36);


                    pushFollow(FOLLOW_atom_in_unaryExp406);
                    atom37=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom37.getTree());

                    // AST REWRITE
                    // elements: atom
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 59:18: -> ^( NEG atom )
                    {
                        // ScriptGrammar.g:59:21: ^( NEG atom )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(NEG, "NEG")
                        , root_1);

                        adaptor.addChild(root_1, stream_atom.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // ScriptGrammar.g:60:9: '~' atom
                    {
                    char_literal38=(Token)match(input,38,FOLLOW_38_in_unaryExp424); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_38.add(char_literal38);


                    pushFollow(FOLLOW_atom_in_unaryExp426);
                    atom39=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom39.getTree());

                    // AST REWRITE
                    // elements: atom, 38
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 60:18: -> ^( '~' atom )
                    {
                        // ScriptGrammar.g:60:21: ^( '~' atom )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_38.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_atom.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // ScriptGrammar.g:61:9: atom
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_atom_in_unaryExp444);
                    atom40=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, atom40.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "unaryExp"


    public static class atom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "atom"
    // ScriptGrammar.g:64:1: atom : ( INT | ID | '(' expr ')' -> expr | funcall );
    public final atom_return atom() throws RecognitionException {
        atom_return retval = new atom_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token INT41=null;
        Token ID42=null;
        Token char_literal43=null;
        Token char_literal45=null;
        expr_return expr44 =null;

        funcall_return funcall46 =null;


        CommonTree INT41_tree=null;
        CommonTree ID42_tree=null;
        CommonTree char_literal43_tree=null;
        CommonTree char_literal45_tree=null;
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_14=new RewriteRuleTokenStream(adaptor,"token 14");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // ScriptGrammar.g:64:5: ( INT | ID | '(' expr ')' -> expr | funcall )
            int alt11=4;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt11=1;
                }
                break;
            case ID:
                {
                int LA11_2 = input.LA(2);

                if ( (LA11_2==14) ) {
                    alt11=4;
                }
                else if ( (LA11_2==EOF||LA11_2==NEWLINE||(LA11_2 >= 12 && LA11_2 <= 13)||(LA11_2 >= 15 && LA11_2 <= 20)||(LA11_2 >= 22 && LA11_2 <= 23)||(LA11_2 >= 29 && LA11_2 <= 30)||LA11_2==36) ) {
                    alt11=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 2, input);

                    throw nvae;

                }
                }
                break;
            case 14:
                {
                alt11=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }

            switch (alt11) {
                case 1 :
                    // ScriptGrammar.g:64:9: INT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    INT41=(Token)match(input,INT,FOLLOW_INT_in_atom462); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT41_tree = 
                    (CommonTree)adaptor.create(INT41)
                    ;
                    adaptor.addChild(root_0, INT41_tree);
                    }

                    }
                    break;
                case 2 :
                    // ScriptGrammar.g:65:9: ID
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ID42=(Token)match(input,ID,FOLLOW_ID_in_atom473); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ID42_tree = 
                    (CommonTree)adaptor.create(ID42)
                    ;
                    adaptor.addChild(root_0, ID42_tree);
                    }

                    }
                    break;
                case 3 :
                    // ScriptGrammar.g:66:9: '(' expr ')'
                    {
                    char_literal43=(Token)match(input,14,FOLLOW_14_in_atom483); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_14.add(char_literal43);


                    pushFollow(FOLLOW_expr_in_atom485);
                    expr44=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr44.getTree());

                    char_literal45=(Token)match(input,15,FOLLOW_15_in_atom487); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_15.add(char_literal45);


                    // AST REWRITE
                    // elements: expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 66:22: -> expr
                    {
                        adaptor.addChild(root_0, stream_expr.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // ScriptGrammar.g:67:9: funcall
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_funcall_in_atom501);
                    funcall46=funcall();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, funcall46.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "atom"


    public static class funcall_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "funcall"
    // ScriptGrammar.g:70:1: funcall : ID '(' ( expr )? ( ',' expr )* ')' -> ^( CALL ID ( expr )* ) ;
    public final funcall_return funcall() throws RecognitionException {
        funcall_return retval = new funcall_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID47=null;
        Token char_literal48=null;
        Token char_literal50=null;
        Token char_literal52=null;
        expr_return expr49 =null;

        expr_return expr51 =null;


        CommonTree ID47_tree=null;
        CommonTree char_literal48_tree=null;
        CommonTree char_literal50_tree=null;
        CommonTree char_literal52_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_18=new RewriteRuleTokenStream(adaptor,"token 18");
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_14=new RewriteRuleTokenStream(adaptor,"token 14");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // ScriptGrammar.g:70:8: ( ID '(' ( expr )? ( ',' expr )* ')' -> ^( CALL ID ( expr )* ) )
            // ScriptGrammar.g:70:10: ID '(' ( expr )? ( ',' expr )* ')'
            {
            ID47=(Token)match(input,ID,FOLLOW_ID_in_funcall513); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID47);


            char_literal48=(Token)match(input,14,FOLLOW_14_in_funcall515); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_14.add(char_literal48);


            // ScriptGrammar.g:70:17: ( expr )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0 >= ID && LA12_0 <= INT)||LA12_0==14||LA12_0==19||LA12_0==38) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ScriptGrammar.g:70:17: expr
                    {
                    pushFollow(FOLLOW_expr_in_funcall517);
                    expr49=expr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expr.add(expr49.getTree());

                    }
                    break;

            }


            // ScriptGrammar.g:70:23: ( ',' expr )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==18) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ScriptGrammar.g:70:24: ',' expr
            	    {
            	    char_literal50=(Token)match(input,18,FOLLOW_18_in_funcall521); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_18.add(char_literal50);


            	    pushFollow(FOLLOW_expr_in_funcall523);
            	    expr51=expr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_expr.add(expr51.getTree());

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            char_literal52=(Token)match(input,15,FOLLOW_15_in_funcall527); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_15.add(char_literal52);


            // AST REWRITE
            // elements: ID, expr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 70:39: -> ^( CALL ID ( expr )* )
            {
                // ScriptGrammar.g:70:42: ^( CALL ID ( expr )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(CALL, "CALL")
                , root_1);

                adaptor.addChild(root_1, 
                stream_ID.nextNode()
                );

                // ScriptGrammar.g:70:52: ( expr )*
                while ( stream_expr.hasNext() ) {
                    adaptor.addChild(root_1, stream_expr.nextTree());

                }
                stream_expr.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "funcall"


    public static class ifexp_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ifexp"
    // ScriptGrammar.g:73:1: ifexp : ( ( ifelseexp )=> ifelseexp | 'if' ( '(' )? boolexp ( ')' )? statblock -> ^( 'if' boolexp statblock ) );
    public final ifexp_return ifexp() throws RecognitionException {
        ifexp_return retval = new ifexp_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal54=null;
        Token char_literal55=null;
        Token char_literal57=null;
        ifelseexp_return ifelseexp53 =null;

        boolexp_return boolexp56 =null;

        statblock_return statblock58 =null;


        CommonTree string_literal54_tree=null;
        CommonTree char_literal55_tree=null;
        CommonTree char_literal57_tree=null;
        RewriteRuleTokenStream stream_32=new RewriteRuleTokenStream(adaptor,"token 32");
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_14=new RewriteRuleTokenStream(adaptor,"token 14");
        RewriteRuleSubtreeStream stream_statblock=new RewriteRuleSubtreeStream(adaptor,"rule statblock");
        RewriteRuleSubtreeStream stream_boolexp=new RewriteRuleSubtreeStream(adaptor,"rule boolexp");
        try {
            // ScriptGrammar.g:73:6: ( ( ifelseexp )=> ifelseexp | 'if' ( '(' )? boolexp ( ')' )? statblock -> ^( 'if' boolexp statblock ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==32) ) {
                int LA16_1 = input.LA(2);

                if ( (synpred2_ScriptGrammar()) ) {
                    alt16=1;
                }
                else if ( (true) ) {
                    alt16=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // ScriptGrammar.g:73:9: ( ifelseexp )=> ifelseexp
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_ifelseexp_in_ifexp557);
                    ifelseexp53=ifelseexp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ifelseexp53.getTree());

                    }
                    break;
                case 2 :
                    // ScriptGrammar.g:74:10: 'if' ( '(' )? boolexp ( ')' )? statblock
                    {
                    string_literal54=(Token)match(input,32,FOLLOW_32_in_ifexp568); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_32.add(string_literal54);


                    // ScriptGrammar.g:74:15: ( '(' )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==14) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // ScriptGrammar.g:74:15: '('
                            {
                            char_literal55=(Token)match(input,14,FOLLOW_14_in_ifexp570); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_14.add(char_literal55);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_boolexp_in_ifexp573);
                    boolexp56=boolexp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_boolexp.add(boolexp56.getTree());

                    // ScriptGrammar.g:74:28: ( ')' )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==15) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // ScriptGrammar.g:74:28: ')'
                            {
                            char_literal57=(Token)match(input,15,FOLLOW_15_in_ifexp575); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_15.add(char_literal57);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_statblock_in_ifexp578);
                    statblock58=statblock();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_statblock.add(statblock58.getTree());

                    // AST REWRITE
                    // elements: statblock, 32, boolexp
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 74:43: -> ^( 'if' boolexp statblock )
                    {
                        // ScriptGrammar.g:74:46: ^( 'if' boolexp statblock )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_32.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_boolexp.nextTree());

                        adaptor.addChild(root_1, stream_statblock.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "ifexp"


    public static class ifelseexp_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ifelseexp"
    // ScriptGrammar.g:77:1: ifelseexp : 'if' ( '(' )? boolexp ( ')' )? statblock ( NEWLINE )* 'else' statblock -> ^( 'if' boolexp ( statblock )* ) ;
    public final ifelseexp_return ifelseexp() throws RecognitionException {
        ifelseexp_return retval = new ifelseexp_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal59=null;
        Token char_literal60=null;
        Token char_literal62=null;
        Token NEWLINE64=null;
        Token string_literal65=null;
        boolexp_return boolexp61 =null;

        statblock_return statblock63 =null;

        statblock_return statblock66 =null;


        CommonTree string_literal59_tree=null;
        CommonTree char_literal60_tree=null;
        CommonTree char_literal62_tree=null;
        CommonTree NEWLINE64_tree=null;
        CommonTree string_literal65_tree=null;
        RewriteRuleTokenStream stream_30=new RewriteRuleTokenStream(adaptor,"token 30");
        RewriteRuleTokenStream stream_32=new RewriteRuleTokenStream(adaptor,"token 32");
        RewriteRuleTokenStream stream_NEWLINE=new RewriteRuleTokenStream(adaptor,"token NEWLINE");
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_14=new RewriteRuleTokenStream(adaptor,"token 14");
        RewriteRuleSubtreeStream stream_statblock=new RewriteRuleSubtreeStream(adaptor,"rule statblock");
        RewriteRuleSubtreeStream stream_boolexp=new RewriteRuleSubtreeStream(adaptor,"rule boolexp");
        try {
            // ScriptGrammar.g:77:10: ( 'if' ( '(' )? boolexp ( ')' )? statblock ( NEWLINE )* 'else' statblock -> ^( 'if' boolexp ( statblock )* ) )
            // ScriptGrammar.g:77:12: 'if' ( '(' )? boolexp ( ')' )? statblock ( NEWLINE )* 'else' statblock
            {
            string_literal59=(Token)match(input,32,FOLLOW_32_in_ifelseexp604); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_32.add(string_literal59);


            // ScriptGrammar.g:77:17: ( '(' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==14) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ScriptGrammar.g:77:17: '('
                    {
                    char_literal60=(Token)match(input,14,FOLLOW_14_in_ifelseexp606); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_14.add(char_literal60);


                    }
                    break;

            }


            pushFollow(FOLLOW_boolexp_in_ifelseexp609);
            boolexp61=boolexp();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_boolexp.add(boolexp61.getTree());

            // ScriptGrammar.g:77:30: ( ')' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==15) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ScriptGrammar.g:77:30: ')'
                    {
                    char_literal62=(Token)match(input,15,FOLLOW_15_in_ifelseexp611); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_15.add(char_literal62);


                    }
                    break;

            }


            pushFollow(FOLLOW_statblock_in_ifelseexp614);
            statblock63=statblock();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_statblock.add(statblock63.getTree());

            // ScriptGrammar.g:77:45: ( NEWLINE )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==NEWLINE) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ScriptGrammar.g:77:45: NEWLINE
            	    {
            	    NEWLINE64=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_ifelseexp616); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_NEWLINE.add(NEWLINE64);


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


            string_literal65=(Token)match(input,30,FOLLOW_30_in_ifelseexp619); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_30.add(string_literal65);


            pushFollow(FOLLOW_statblock_in_ifelseexp621);
            statblock66=statblock();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_statblock.add(statblock66.getTree());

            // AST REWRITE
            // elements: boolexp, 32, statblock
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 77:71: -> ^( 'if' boolexp ( statblock )* )
            {
                // ScriptGrammar.g:77:74: ^( 'if' boolexp ( statblock )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_32.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_boolexp.nextTree());

                // ScriptGrammar.g:77:89: ( statblock )*
                while ( stream_statblock.hasNext() ) {
                    adaptor.addChild(root_1, stream_statblock.nextTree());

                }
                stream_statblock.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "ifelseexp"


    public static class whileexp_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "whileexp"
    // ScriptGrammar.g:80:1: whileexp : 'while' ( '(' )? boolexp ( ')' )? statblock -> ^( 'while' boolexp statblock ) ;
    public final whileexp_return whileexp() throws RecognitionException {
        whileexp_return retval = new whileexp_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal67=null;
        Token char_literal68=null;
        Token char_literal70=null;
        boolexp_return boolexp69 =null;

        statblock_return statblock71 =null;


        CommonTree string_literal67_tree=null;
        CommonTree char_literal68_tree=null;
        CommonTree char_literal70_tree=null;
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_34=new RewriteRuleTokenStream(adaptor,"token 34");
        RewriteRuleTokenStream stream_14=new RewriteRuleTokenStream(adaptor,"token 14");
        RewriteRuleSubtreeStream stream_statblock=new RewriteRuleSubtreeStream(adaptor,"rule statblock");
        RewriteRuleSubtreeStream stream_boolexp=new RewriteRuleSubtreeStream(adaptor,"rule boolexp");
        try {
            // ScriptGrammar.g:80:9: ( 'while' ( '(' )? boolexp ( ')' )? statblock -> ^( 'while' boolexp statblock ) )
            // ScriptGrammar.g:80:12: 'while' ( '(' )? boolexp ( ')' )? statblock
            {
            string_literal67=(Token)match(input,34,FOLLOW_34_in_whileexp649); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_34.add(string_literal67);


            // ScriptGrammar.g:80:20: ( '(' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==14) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ScriptGrammar.g:80:20: '('
                    {
                    char_literal68=(Token)match(input,14,FOLLOW_14_in_whileexp651); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_14.add(char_literal68);


                    }
                    break;

            }


            pushFollow(FOLLOW_boolexp_in_whileexp654);
            boolexp69=boolexp();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_boolexp.add(boolexp69.getTree());

            // ScriptGrammar.g:80:33: ( ')' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==15) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ScriptGrammar.g:80:33: ')'
                    {
                    char_literal70=(Token)match(input,15,FOLLOW_15_in_whileexp656); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_15.add(char_literal70);


                    }
                    break;

            }


            pushFollow(FOLLOW_statblock_in_whileexp659);
            statblock71=statblock();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_statblock.add(statblock71.getTree());

            // AST REWRITE
            // elements: statblock, 34, boolexp
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 80:48: -> ^( 'while' boolexp statblock )
            {
                // ScriptGrammar.g:80:51: ^( 'while' boolexp statblock )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_34.nextNode()
                , root_1);

                adaptor.addChild(root_1, stream_boolexp.nextTree());

                adaptor.addChild(root_1, stream_statblock.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "whileexp"


    public static class boolexp_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "boolexp"
    // ScriptGrammar.g:83:1: boolexp : boolterm ( '==' ^| '!=' ^| '>' ^| '>=' ^| '<' ^| '<=' ^) boolterm ;
    public final boolexp_return boolexp() throws RecognitionException {
        boolexp_return retval = new boolexp_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal73=null;
        Token string_literal74=null;
        Token char_literal75=null;
        Token string_literal76=null;
        Token char_literal77=null;
        Token string_literal78=null;
        boolterm_return boolterm72 =null;

        boolterm_return boolterm79 =null;


        CommonTree string_literal73_tree=null;
        CommonTree string_literal74_tree=null;
        CommonTree char_literal75_tree=null;
        CommonTree string_literal76_tree=null;
        CommonTree char_literal77_tree=null;
        CommonTree string_literal78_tree=null;

        try {
            // ScriptGrammar.g:83:8: ( boolterm ( '==' ^| '!=' ^| '>' ^| '>=' ^| '<' ^| '<=' ^) boolterm )
            // ScriptGrammar.g:83:11: boolterm ( '==' ^| '!=' ^| '>' ^| '>=' ^| '<' ^| '<=' ^) boolterm
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_boolterm_in_boolexp686);
            boolterm72=boolterm();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, boolterm72.getTree());

            // ScriptGrammar.g:83:20: ( '==' ^| '!=' ^| '>' ^| '>=' ^| '<' ^| '<=' ^)
            int alt22=6;
            switch ( input.LA(1) ) {
            case 26:
                {
                alt22=1;
                }
                break;
            case 11:
                {
                alt22=2;
                }
                break;
            case 27:
                {
                alt22=3;
                }
                break;
            case 28:
                {
                alt22=4;
                }
                break;
            case 21:
                {
                alt22=5;
                }
                break;
            case 24:
                {
                alt22=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }

            switch (alt22) {
                case 1 :
                    // ScriptGrammar.g:83:21: '==' ^
                    {
                    string_literal73=(Token)match(input,26,FOLLOW_26_in_boolexp689); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal73_tree = 
                    (CommonTree)adaptor.create(string_literal73)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal73_tree, root_0);
                    }

                    }
                    break;
                case 2 :
                    // ScriptGrammar.g:83:27: '!=' ^
                    {
                    string_literal74=(Token)match(input,11,FOLLOW_11_in_boolexp692); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal74_tree = 
                    (CommonTree)adaptor.create(string_literal74)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal74_tree, root_0);
                    }

                    }
                    break;
                case 3 :
                    // ScriptGrammar.g:83:33: '>' ^
                    {
                    char_literal75=(Token)match(input,27,FOLLOW_27_in_boolexp695); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal75_tree = 
                    (CommonTree)adaptor.create(char_literal75)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal75_tree, root_0);
                    }

                    }
                    break;
                case 4 :
                    // ScriptGrammar.g:83:38: '>=' ^
                    {
                    string_literal76=(Token)match(input,28,FOLLOW_28_in_boolexp698); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal76_tree = 
                    (CommonTree)adaptor.create(string_literal76)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal76_tree, root_0);
                    }

                    }
                    break;
                case 5 :
                    // ScriptGrammar.g:83:44: '<' ^
                    {
                    char_literal77=(Token)match(input,21,FOLLOW_21_in_boolexp701); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal77_tree = 
                    (CommonTree)adaptor.create(char_literal77)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(char_literal77_tree, root_0);
                    }

                    }
                    break;
                case 6 :
                    // ScriptGrammar.g:83:49: '<=' ^
                    {
                    string_literal78=(Token)match(input,24,FOLLOW_24_in_boolexp704); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal78_tree = 
                    (CommonTree)adaptor.create(string_literal78)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(string_literal78_tree, root_0);
                    }

                    }
                    break;

            }


            pushFollow(FOLLOW_boolterm_in_boolexp708);
            boolterm79=boolterm();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, boolterm79.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "boolexp"


    public static class boolterm_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "boolterm"
    // ScriptGrammar.g:86:1: boolterm : ( ID | INT ) ;
    public final boolterm_return boolterm() throws RecognitionException {
        boolterm_return retval = new boolterm_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token set80=null;

        CommonTree set80_tree=null;

        try {
            // ScriptGrammar.g:86:9: ( ( ID | INT ) )
            // ScriptGrammar.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            set80=(Token)input.LT(1);

            if ( (input.LA(1) >= ID && input.LA(1) <= INT) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(set80)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "boolterm"


    public static class forexp_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "forexp"
    // ScriptGrammar.g:89:1: forexp : 'for' ( '(' )? ID ( ',' forterm )* ( ')' )? statblock -> ^( 'for' ID ( forterm )* statblock ) ;
    public final forexp_return forexp() throws RecognitionException {
        forexp_return retval = new forexp_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token string_literal81=null;
        Token char_literal82=null;
        Token ID83=null;
        Token char_literal84=null;
        Token char_literal86=null;
        forterm_return forterm85 =null;

        statblock_return statblock87 =null;


        CommonTree string_literal81_tree=null;
        CommonTree char_literal82_tree=null;
        CommonTree ID83_tree=null;
        CommonTree char_literal84_tree=null;
        CommonTree char_literal86_tree=null;
        RewriteRuleTokenStream stream_31=new RewriteRuleTokenStream(adaptor,"token 31");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_18=new RewriteRuleTokenStream(adaptor,"token 18");
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");
        RewriteRuleTokenStream stream_14=new RewriteRuleTokenStream(adaptor,"token 14");
        RewriteRuleSubtreeStream stream_forterm=new RewriteRuleSubtreeStream(adaptor,"rule forterm");
        RewriteRuleSubtreeStream stream_statblock=new RewriteRuleSubtreeStream(adaptor,"rule statblock");
        try {
            // ScriptGrammar.g:89:7: ( 'for' ( '(' )? ID ( ',' forterm )* ( ')' )? statblock -> ^( 'for' ID ( forterm )* statblock ) )
            // ScriptGrammar.g:89:9: 'for' ( '(' )? ID ( ',' forterm )* ( ')' )? statblock
            {
            string_literal81=(Token)match(input,31,FOLLOW_31_in_forexp740); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_31.add(string_literal81);


            // ScriptGrammar.g:89:15: ( '(' )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==14) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ScriptGrammar.g:89:15: '('
                    {
                    char_literal82=(Token)match(input,14,FOLLOW_14_in_forexp742); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_14.add(char_literal82);


                    }
                    break;

            }


            ID83=(Token)match(input,ID,FOLLOW_ID_in_forexp745); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID83);


            // ScriptGrammar.g:89:23: ( ',' forterm )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==18) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ScriptGrammar.g:89:24: ',' forterm
            	    {
            	    char_literal84=(Token)match(input,18,FOLLOW_18_in_forexp748); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_18.add(char_literal84);


            	    pushFollow(FOLLOW_forterm_in_forexp750);
            	    forterm85=forterm();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_forterm.add(forterm85.getTree());

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            // ScriptGrammar.g:89:38: ( ')' )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==15) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ScriptGrammar.g:89:38: ')'
                    {
                    char_literal86=(Token)match(input,15,FOLLOW_15_in_forexp754); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_15.add(char_literal86);


                    }
                    break;

            }


            pushFollow(FOLLOW_statblock_in_forexp757);
            statblock87=statblock();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_statblock.add(statblock87.getTree());

            // AST REWRITE
            // elements: statblock, forterm, 31, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 89:53: -> ^( 'for' ID ( forterm )* statblock )
            {
                // ScriptGrammar.g:89:56: ^( 'for' ID ( forterm )* statblock )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                stream_31.nextNode()
                , root_1);

                adaptor.addChild(root_1, 
                stream_ID.nextNode()
                );

                // ScriptGrammar.g:89:67: ( forterm )*
                while ( stream_forterm.hasNext() ) {
                    adaptor.addChild(root_1, stream_forterm.nextTree());

                }
                stream_forterm.reset();

                adaptor.addChild(root_1, stream_statblock.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "forexp"


    public static class forterm_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "forterm"
    // ScriptGrammar.g:92:1: forterm : ( ID | INT ) ;
    public final forterm_return forterm() throws RecognitionException {
        forterm_return retval = new forterm_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token set88=null;

        CommonTree set88_tree=null;

        try {
            // ScriptGrammar.g:92:8: ( ( ID | INT ) )
            // ScriptGrammar.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            set88=(Token)input.LT(1);

            if ( (input.LA(1) >= ID && input.LA(1) <= INT) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(set88)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "forterm"

    // $ANTLR start synpred1_ScriptGrammar
    public final void synpred1_ScriptGrammar_fragment() throws RecognitionException {
        // ScriptGrammar.g:33:12: ( '{' )
        // ScriptGrammar.g:33:13: '{'
        {
        match(input,35,FOLLOW_35_in_synpred1_ScriptGrammar160); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_ScriptGrammar

    // $ANTLR start synpred2_ScriptGrammar
    public final void synpred2_ScriptGrammar_fragment() throws RecognitionException {
        // ScriptGrammar.g:73:9: ( ifelseexp )
        // ScriptGrammar.g:73:10: ifelseexp
        {
        pushFollow(FOLLOW_ifelseexp_in_synpred2_ScriptGrammar552);
        ifelseexp();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_ScriptGrammar

    // Delegated rules

    public final boolean synpred1_ScriptGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_ScriptGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_ScriptGrammar() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_ScriptGrammar_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_block_in_prog91 = new BitSet(new long[]{0x0000004F800842C2L});
    public static final BitSet FOLLOW_35_in_block101 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NEWLINE_in_block103 = new BitSet(new long[]{0x0000006F800842C0L});
    public static final BitSet FOLLOW_block_in_block105 = new BitSet(new long[]{0x0000006F800842C0L});
    public static final BitSet FOLLOW_37_in_block108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stat_in_block127 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_NEWLINE_in_block129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_block143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statblock165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stat_in_statblock178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_stat192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_stat202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_retexp_in_stat212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifexp_in_stat222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileexp_in_stat232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forexp_in_stat242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_assign258 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_assign260 = new BitSet(new long[]{0x00000040000840C0L});
    public static final BitSet FOLLOW_expr_in_assign262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_retexp288 = new BitSet(new long[]{0x00000040000840C0L});
    public static final BitSet FOLLOW_expr_in_retexp290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpr_in_expr316 = new BitSet(new long[]{0x0000001020CA2002L});
    public static final BitSet FOLLOW_17_in_expr320 = new BitSet(new long[]{0x00000040000840C0L});
    public static final BitSet FOLLOW_19_in_expr323 = new BitSet(new long[]{0x00000040000840C0L});
    public static final BitSet FOLLOW_13_in_expr326 = new BitSet(new long[]{0x00000040000840C0L});
    public static final BitSet FOLLOW_36_in_expr329 = new BitSet(new long[]{0x00000040000840C0L});
    public static final BitSet FOLLOW_29_in_expr332 = new BitSet(new long[]{0x00000040000840C0L});
    public static final BitSet FOLLOW_22_in_expr335 = new BitSet(new long[]{0x00000040000840C0L});
    public static final BitSet FOLLOW_23_in_expr338 = new BitSet(new long[]{0x00000040000840C0L});
    public static final BitSet FOLLOW_multExpr_in_expr342 = new BitSet(new long[]{0x0000001020CA2002L});
    public static final BitSet FOLLOW_unaryExp_in_multExpr364 = new BitSet(new long[]{0x0000000000111002L});
    public static final BitSet FOLLOW_16_in_multExpr368 = new BitSet(new long[]{0x00000040000840C0L});
    public static final BitSet FOLLOW_20_in_multExpr371 = new BitSet(new long[]{0x00000040000840C0L});
    public static final BitSet FOLLOW_12_in_multExpr374 = new BitSet(new long[]{0x00000040000840C0L});
    public static final BitSet FOLLOW_unaryExp_in_multExpr378 = new BitSet(new long[]{0x0000000000111002L});
    public static final BitSet FOLLOW_19_in_unaryExp404 = new BitSet(new long[]{0x00000000000040C0L});
    public static final BitSet FOLLOW_atom_in_unaryExp406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_unaryExp424 = new BitSet(new long[]{0x00000000000040C0L});
    public static final BitSet FOLLOW_atom_in_unaryExp426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_unaryExp444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_atom462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_atom473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_atom483 = new BitSet(new long[]{0x00000040000840C0L});
    public static final BitSet FOLLOW_expr_in_atom485 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_atom487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funcall_in_atom501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_funcall513 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_funcall515 = new BitSet(new long[]{0x00000040000CC0C0L});
    public static final BitSet FOLLOW_expr_in_funcall517 = new BitSet(new long[]{0x0000000000048000L});
    public static final BitSet FOLLOW_18_in_funcall521 = new BitSet(new long[]{0x00000040000840C0L});
    public static final BitSet FOLLOW_expr_in_funcall523 = new BitSet(new long[]{0x0000000000048000L});
    public static final BitSet FOLLOW_15_in_funcall527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifelseexp_in_ifexp557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ifexp568 = new BitSet(new long[]{0x00000000000040C0L});
    public static final BitSet FOLLOW_14_in_ifexp570 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_boolexp_in_ifexp573 = new BitSet(new long[]{0x0000004F8008C2C0L});
    public static final BitSet FOLLOW_15_in_ifexp575 = new BitSet(new long[]{0x0000004F800842C0L});
    public static final BitSet FOLLOW_statblock_in_ifexp578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ifelseexp604 = new BitSet(new long[]{0x00000000000040C0L});
    public static final BitSet FOLLOW_14_in_ifelseexp606 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_boolexp_in_ifelseexp609 = new BitSet(new long[]{0x0000004F8008C2C0L});
    public static final BitSet FOLLOW_15_in_ifelseexp611 = new BitSet(new long[]{0x0000004F800842C0L});
    public static final BitSet FOLLOW_statblock_in_ifelseexp614 = new BitSet(new long[]{0x0000000040000200L});
    public static final BitSet FOLLOW_NEWLINE_in_ifelseexp616 = new BitSet(new long[]{0x0000000040000200L});
    public static final BitSet FOLLOW_30_in_ifelseexp619 = new BitSet(new long[]{0x0000004F800842C0L});
    public static final BitSet FOLLOW_statblock_in_ifelseexp621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_whileexp649 = new BitSet(new long[]{0x00000000000040C0L});
    public static final BitSet FOLLOW_14_in_whileexp651 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_boolexp_in_whileexp654 = new BitSet(new long[]{0x0000004F8008C2C0L});
    public static final BitSet FOLLOW_15_in_whileexp656 = new BitSet(new long[]{0x0000004F800842C0L});
    public static final BitSet FOLLOW_statblock_in_whileexp659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boolterm_in_boolexp686 = new BitSet(new long[]{0x000000001D200800L});
    public static final BitSet FOLLOW_26_in_boolexp689 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_11_in_boolexp692 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_27_in_boolexp695 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_28_in_boolexp698 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_21_in_boolexp701 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_24_in_boolexp704 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_boolterm_in_boolexp708 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_forexp740 = new BitSet(new long[]{0x0000000000004040L});
    public static final BitSet FOLLOW_14_in_forexp742 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ID_in_forexp745 = new BitSet(new long[]{0x0000004F800CC2C0L});
    public static final BitSet FOLLOW_18_in_forexp748 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_forterm_in_forexp750 = new BitSet(new long[]{0x0000004F800CC2C0L});
    public static final BitSet FOLLOW_15_in_forexp754 = new BitSet(new long[]{0x0000004F800842C0L});
    public static final BitSet FOLLOW_statblock_in_forexp757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_synpred1_ScriptGrammar160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifelseexp_in_synpred2_ScriptGrammar552 = new BitSet(new long[]{0x0000000000000002L});

}