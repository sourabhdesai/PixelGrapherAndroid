// $ANTLR 3.4 ScriptGrammar.g 2012-05-20 13:30:07

package com.example.PixelBin.CodeGeneration;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class ScriptGrammarLexer extends Lexer {
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
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public ScriptGrammarLexer() {} 
    public ScriptGrammarLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public ScriptGrammarLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "ScriptGrammar.g"; }

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:6:7: ( '!=' )
            // ScriptGrammar.g:6:9: '!='
            {
            match("!="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:7:7: ( '%' )
            // ScriptGrammar.g:7:9: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:8:7: ( '&' )
            // ScriptGrammar.g:8:9: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:9:7: ( '(' )
            // ScriptGrammar.g:9:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:10:7: ( ')' )
            // ScriptGrammar.g:10:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:11:7: ( '*' )
            // ScriptGrammar.g:11:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:12:7: ( '+' )
            // ScriptGrammar.g:12:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:13:7: ( ',' )
            // ScriptGrammar.g:13:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:14:7: ( '-' )
            // ScriptGrammar.g:14:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:15:7: ( '/' )
            // ScriptGrammar.g:15:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:16:7: ( '<' )
            // ScriptGrammar.g:16:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:17:7: ( '<<' )
            // ScriptGrammar.g:17:9: '<<'
            {
            match("<<"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:18:7: ( '<<<' )
            // ScriptGrammar.g:18:9: '<<<'
            {
            match("<<<"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:19:7: ( '<=' )
            // ScriptGrammar.g:19:9: '<='
            {
            match("<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:20:7: ( '=' )
            // ScriptGrammar.g:20:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:21:7: ( '==' )
            // ScriptGrammar.g:21:9: '=='
            {
            match("=="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:22:7: ( '>' )
            // ScriptGrammar.g:22:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:23:7: ( '>=' )
            // ScriptGrammar.g:23:9: '>='
            {
            match(">="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:24:7: ( '>>' )
            // ScriptGrammar.g:24:9: '>>'
            {
            match(">>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:25:7: ( 'else' )
            // ScriptGrammar.g:25:9: 'else'
            {
            match("else"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:26:7: ( 'for' )
            // ScriptGrammar.g:26:9: 'for'
            {
            match("for"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:27:7: ( 'if' )
            // ScriptGrammar.g:27:9: 'if'
            {
            match("if"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:28:7: ( 'return' )
            // ScriptGrammar.g:28:9: 'return'
            {
            match("return"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:29:7: ( 'while' )
            // ScriptGrammar.g:29:9: 'while'
            {
            match("while"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:30:7: ( '{' )
            // ScriptGrammar.g:30:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:31:7: ( '|' )
            // ScriptGrammar.g:31:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:32:7: ( '}' )
            // ScriptGrammar.g:32:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:33:7: ( '~' )
            // ScriptGrammar.g:33:9: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:95:5: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
            // ScriptGrammar.g:95:9: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // ScriptGrammar.g:95:28: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ScriptGrammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:96:5: ( ( '0' .. '9' )+ )
            // ScriptGrammar.g:96:9: ( '0' .. '9' )+
            {
            // ScriptGrammar.g:96:9: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ScriptGrammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:97:8: ( ( '\\r' )? '\\n' )
            // ScriptGrammar.g:97:9: ( '\\r' )? '\\n'
            {
            // ScriptGrammar.g:97:9: ( '\\r' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\r') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ScriptGrammar.g:97:9: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ScriptGrammar.g:98:5: ( ( ' ' | '\\t' )+ )
            // ScriptGrammar.g:98:9: ( ' ' | '\\t' )+
            {
            // ScriptGrammar.g:98:9: ( ' ' | '\\t' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='\t'||LA4_0==' ') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ScriptGrammar.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            skip();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // ScriptGrammar.g:1:8: ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | ID | INT | NEWLINE | WS )
        int alt5=32;
        switch ( input.LA(1) ) {
        case '!':
            {
            alt5=1;
            }
            break;
        case '%':
            {
            alt5=2;
            }
            break;
        case '&':
            {
            alt5=3;
            }
            break;
        case '(':
            {
            alt5=4;
            }
            break;
        case ')':
            {
            alt5=5;
            }
            break;
        case '*':
            {
            alt5=6;
            }
            break;
        case '+':
            {
            alt5=7;
            }
            break;
        case ',':
            {
            alt5=8;
            }
            break;
        case '-':
            {
            alt5=9;
            }
            break;
        case '/':
            {
            alt5=10;
            }
            break;
        case '<':
            {
            switch ( input.LA(2) ) {
            case '<':
                {
                int LA5_27 = input.LA(3);

                if ( (LA5_27=='<') ) {
                    alt5=13;
                }
                else {
                    alt5=12;
                }
                }
                break;
            case '=':
                {
                alt5=14;
                }
                break;
            default:
                alt5=11;
            }

            }
            break;
        case '=':
            {
            int LA5_12 = input.LA(2);

            if ( (LA5_12=='=') ) {
                alt5=16;
            }
            else {
                alt5=15;
            }
            }
            break;
        case '>':
            {
            switch ( input.LA(2) ) {
            case '=':
                {
                alt5=18;
                }
                break;
            case '>':
                {
                alt5=19;
                }
                break;
            default:
                alt5=17;
            }

            }
            break;
        case 'e':
            {
            int LA5_14 = input.LA(2);

            if ( (LA5_14=='l') ) {
                int LA5_35 = input.LA(3);

                if ( (LA5_35=='s') ) {
                    int LA5_42 = input.LA(4);

                    if ( (LA5_42=='e') ) {
                        int LA5_47 = input.LA(5);

                        if ( ((LA5_47 >= '0' && LA5_47 <= '9')||(LA5_47 >= 'A' && LA5_47 <= 'Z')||(LA5_47 >= 'a' && LA5_47 <= 'z')) ) {
                            alt5=29;
                        }
                        else {
                            alt5=20;
                        }
                    }
                    else {
                        alt5=29;
                    }
                }
                else {
                    alt5=29;
                }
            }
            else {
                alt5=29;
            }
            }
            break;
        case 'f':
            {
            int LA5_15 = input.LA(2);

            if ( (LA5_15=='o') ) {
                int LA5_36 = input.LA(3);

                if ( (LA5_36=='r') ) {
                    int LA5_43 = input.LA(4);

                    if ( ((LA5_43 >= '0' && LA5_43 <= '9')||(LA5_43 >= 'A' && LA5_43 <= 'Z')||(LA5_43 >= 'a' && LA5_43 <= 'z')) ) {
                        alt5=29;
                    }
                    else {
                        alt5=21;
                    }
                }
                else {
                    alt5=29;
                }
            }
            else {
                alt5=29;
            }
            }
            break;
        case 'i':
            {
            int LA5_16 = input.LA(2);

            if ( (LA5_16=='f') ) {
                int LA5_37 = input.LA(3);

                if ( ((LA5_37 >= '0' && LA5_37 <= '9')||(LA5_37 >= 'A' && LA5_37 <= 'Z')||(LA5_37 >= 'a' && LA5_37 <= 'z')) ) {
                    alt5=29;
                }
                else {
                    alt5=22;
                }
            }
            else {
                alt5=29;
            }
            }
            break;
        case 'r':
            {
            int LA5_17 = input.LA(2);

            if ( (LA5_17=='e') ) {
                int LA5_38 = input.LA(3);

                if ( (LA5_38=='t') ) {
                    int LA5_45 = input.LA(4);

                    if ( (LA5_45=='u') ) {
                        int LA5_49 = input.LA(5);

                        if ( (LA5_49=='r') ) {
                            int LA5_52 = input.LA(6);

                            if ( (LA5_52=='n') ) {
                                int LA5_54 = input.LA(7);

                                if ( ((LA5_54 >= '0' && LA5_54 <= '9')||(LA5_54 >= 'A' && LA5_54 <= 'Z')||(LA5_54 >= 'a' && LA5_54 <= 'z')) ) {
                                    alt5=29;
                                }
                                else {
                                    alt5=23;
                                }
                            }
                            else {
                                alt5=29;
                            }
                        }
                        else {
                            alt5=29;
                        }
                    }
                    else {
                        alt5=29;
                    }
                }
                else {
                    alt5=29;
                }
            }
            else {
                alt5=29;
            }
            }
            break;
        case 'w':
            {
            int LA5_18 = input.LA(2);

            if ( (LA5_18=='h') ) {
                int LA5_39 = input.LA(3);

                if ( (LA5_39=='i') ) {
                    int LA5_46 = input.LA(4);

                    if ( (LA5_46=='l') ) {
                        int LA5_50 = input.LA(5);

                        if ( (LA5_50=='e') ) {
                            int LA5_53 = input.LA(6);

                            if ( ((LA5_53 >= '0' && LA5_53 <= '9')||(LA5_53 >= 'A' && LA5_53 <= 'Z')||(LA5_53 >= 'a' && LA5_53 <= 'z')) ) {
                                alt5=29;
                            }
                            else {
                                alt5=24;
                            }
                        }
                        else {
                            alt5=29;
                        }
                    }
                    else {
                        alt5=29;
                    }
                }
                else {
                    alt5=29;
                }
            }
            else {
                alt5=29;
            }
            }
            break;
        case '{':
            {
            alt5=25;
            }
            break;
        case '|':
            {
            alt5=26;
            }
            break;
        case '}':
            {
            alt5=27;
            }
            break;
        case '~':
            {
            alt5=28;
            }
            break;
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'g':
        case 'h':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'o':
        case 'p':
        case 'q':
        case 's':
        case 't':
        case 'u':
        case 'v':
        case 'x':
        case 'y':
        case 'z':
            {
            alt5=29;
            }
            break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt5=30;
            }
            break;
        case '\n':
        case '\r':
            {
            alt5=31;
            }
            break;
        case '\t':
        case ' ':
            {
            alt5=32;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 5, 0, input);

            throw nvae;

        }

        switch (alt5) {
            case 1 :
                // ScriptGrammar.g:1:10: T__11
                {
                mT__11(); 


                }
                break;
            case 2 :
                // ScriptGrammar.g:1:16: T__12
                {
                mT__12(); 


                }
                break;
            case 3 :
                // ScriptGrammar.g:1:22: T__13
                {
                mT__13(); 


                }
                break;
            case 4 :
                // ScriptGrammar.g:1:28: T__14
                {
                mT__14(); 


                }
                break;
            case 5 :
                // ScriptGrammar.g:1:34: T__15
                {
                mT__15(); 


                }
                break;
            case 6 :
                // ScriptGrammar.g:1:40: T__16
                {
                mT__16(); 


                }
                break;
            case 7 :
                // ScriptGrammar.g:1:46: T__17
                {
                mT__17(); 


                }
                break;
            case 8 :
                // ScriptGrammar.g:1:52: T__18
                {
                mT__18(); 


                }
                break;
            case 9 :
                // ScriptGrammar.g:1:58: T__19
                {
                mT__19(); 


                }
                break;
            case 10 :
                // ScriptGrammar.g:1:64: T__20
                {
                mT__20(); 


                }
                break;
            case 11 :
                // ScriptGrammar.g:1:70: T__21
                {
                mT__21(); 


                }
                break;
            case 12 :
                // ScriptGrammar.g:1:76: T__22
                {
                mT__22(); 


                }
                break;
            case 13 :
                // ScriptGrammar.g:1:82: T__23
                {
                mT__23(); 


                }
                break;
            case 14 :
                // ScriptGrammar.g:1:88: T__24
                {
                mT__24(); 


                }
                break;
            case 15 :
                // ScriptGrammar.g:1:94: T__25
                {
                mT__25(); 


                }
                break;
            case 16 :
                // ScriptGrammar.g:1:100: T__26
                {
                mT__26(); 


                }
                break;
            case 17 :
                // ScriptGrammar.g:1:106: T__27
                {
                mT__27(); 


                }
                break;
            case 18 :
                // ScriptGrammar.g:1:112: T__28
                {
                mT__28(); 


                }
                break;
            case 19 :
                // ScriptGrammar.g:1:118: T__29
                {
                mT__29(); 


                }
                break;
            case 20 :
                // ScriptGrammar.g:1:124: T__30
                {
                mT__30(); 


                }
                break;
            case 21 :
                // ScriptGrammar.g:1:130: T__31
                {
                mT__31(); 


                }
                break;
            case 22 :
                // ScriptGrammar.g:1:136: T__32
                {
                mT__32(); 


                }
                break;
            case 23 :
                // ScriptGrammar.g:1:142: T__33
                {
                mT__33(); 


                }
                break;
            case 24 :
                // ScriptGrammar.g:1:148: T__34
                {
                mT__34(); 


                }
                break;
            case 25 :
                // ScriptGrammar.g:1:154: T__35
                {
                mT__35(); 


                }
                break;
            case 26 :
                // ScriptGrammar.g:1:160: T__36
                {
                mT__36(); 


                }
                break;
            case 27 :
                // ScriptGrammar.g:1:166: T__37
                {
                mT__37(); 


                }
                break;
            case 28 :
                // ScriptGrammar.g:1:172: T__38
                {
                mT__38(); 


                }
                break;
            case 29 :
                // ScriptGrammar.g:1:178: ID
                {
                mID(); 


                }
                break;
            case 30 :
                // ScriptGrammar.g:1:181: INT
                {
                mINT(); 


                }
                break;
            case 31 :
                // ScriptGrammar.g:1:185: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 32 :
                // ScriptGrammar.g:1:193: WS
                {
                mWS(); 


                }
                break;

        }

    }


 

}