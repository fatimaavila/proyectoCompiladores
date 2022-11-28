/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.scanner;

/**
 *
 * @author yanet
 */
import org.antlr.v4.runtime.Lexer;
//import org.antlr.v4.runtime.CharStream;
//import org.antlr.v4.runtime.RuntimeMetaData;
//import org.antlr.v4.runtime.Token;
//import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
//import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DecafLexer extends Lexer {

    static {
        RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache
            = new PredictionContextCache();
    public static final int COMMENT = 1, WHITESPACE = 2, HEX_ERROR = 3, INT_LITERAL = 4, DECIMAL_LITERAL = 5,
            CHAR_LITERAL = 6, CHAR_ERROR = 7, STRING_LITERAL = 8, STRING_ERROR = 9, ASIG_OP = 10,
            NEGATION = 11, COND_OP = 12, REL_OP = 13, EQ_OP = 14, BOOL_LITERAL = 15, KW_INT = 16,
            KW_BOOL = 17, KW_CALLOUT = 18, KW_VOID = 19, KW_IF = 20, KW_ELSE = 21, KW_FOR = 22,
            KW_WHILE = 23, KW_RETURN = 24, KW_BREAK = 25, KW_CONTINUE = 26, KW_TRUE = 27, KW_FALSE = 28,
            ID = 29, PARENTESIS_I = 30, PARENTESIS_D = 31, CORCHETE_I = 32, CORCHETE_D = 33,
            LLAVE_I = 34, LLAVE_D = 35, PLUS = 36, MINUS = 37, MULT = 38, DIV = 39, MOD = 40, AND = 41,
            OR = 42, PUNTO_COMA = 43, COMA = 44, ERROR = 45;
    public static String[] modeNames = {
        "DEFAULT_MODE"
    };

    public static final String[] tokenNames = {
        "'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'",
        "'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'",
        "'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'",
        "'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'",
        "'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'",
        "'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''",
        "'('", "')'", "'*'", "'+'", "','", "'-'"
    };
    public static final String[] ruleNames = {
        "COMMENT", "WHITESPACE", "HEX_ERROR", "INT_LITERAL", "DECIMAL_LITERAL",
        "CHAR_LITERAL", "CHAR_ERROR", "STRING_LITERAL", "STRING_ERROR", "ASIG_OP",
        "NEGATION", "COND_OP", "REL_OP", "EQ_OP", "BOOL_LITERAL", "KW_INT", "KW_BOOL",
        "KW_CALLOUT", "KW_VOID", "KW_IF", "KW_ELSE", "KW_FOR", "KW_WHILE", "KW_RETURN",
        "KW_BREAK", "KW_CONTINUE", "KW_TRUE", "KW_FALSE", "ID", "PARENTESIS_I",
        "PARENTESIS_D", "CORCHETE_I", "CORCHETE_D", "LLAVE_I", "LLAVE_D", "PLUS",
        "MINUS", "MULT", "DIV", "MOD", "AND", "OR", "PUNTO_COMA", "COMA", "ERROR",
        "DIGIT", "ALPHA", "ALPHA_NUM", "HEX_DIGIT", "ESCAPE_CHAR", "EQUAL", "HEX_LITERAL"
    };

    public DecafLexer(CharStream input) {
        super(input);
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    public String getGrammarFileName() {
        return "DecafLexer.g";
    }

    @Override
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public String[] getModeNames() {
        return modeNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public static final String _serializedATN
            = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2/\u017c\b\1\4\2\t"
            + "\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"
            + "\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"
            + "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"
            + "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"
            + "\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"
            + ",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"
            + "\64\4\65\t\65\3\2\3\2\3\2\3\2\7\2p\n\2\f\2\16\2s\13\2\3\2\5\2v\n\2\3\2"
            + "\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4\u0082\n\4\f\4\16\4\u0085\13\4"
            + "\3\5\3\5\5\5\u0089\n\5\3\6\6\6\u008c\n\6\r\6\16\6\u008d\3\7\3\7\3\7\3"
            + "\7\5\7\u0094\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u009e\n\b\3\b\3\b"
            + "\6\b\u00a2\n\b\r\b\16\b\u00a3\3\b\3\b\3\b\6\b\u00a9\n\b\r\b\16\b\u00aa"
            + "\5\b\u00ad\n\b\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00b5\n\t\f\t\16\t\u00b8\13"
            + "\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00c2\n\n\f\n\16\n\u00c5\13\n\3"
            + "\n\5\n\u00c8\n\n\3\13\3\13\3\13\3\13\3\13\5\13\u00cf\n\13\3\f\3\f\3\r"
            + "\3\r\5\r\u00d5\n\r\3\16\3\16\3\16\3\16\3\16\5\16\u00dc\n\16\3\17\3\17"
            + "\3\17\3\17\5\17\u00e2\n\17\3\20\3\20\5\20\u00e6\n\20\3\21\3\21\3\21\3"
            + "\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3"
            + "\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3"
            + "\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3"
            + "\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3"
            + "\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3"
            + "\35\3\35\3\35\3\36\3\36\5\36\u0136\n\36\3\36\3\36\7\36\u013a\n\36\f\36"
            + "\16\36\u013d\13\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&"
            + "\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3*\3+\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60"
            + "\3\60\3\61\3\61\5\61\u0167\n\61\3\62\3\62\5\62\u016b\n\62\3\63\3\63\3"
            + "\63\3\63\3\63\3\63\3\64\3\64\3\65\3\65\3\65\3\65\6\65\u0179\n\65\r\65"
            + "\16\65\u017a\3q\2\66\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"
            + "\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"
            + "\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\2_\2a\2c\2e\2"
            + "g\2i\2\3\2\r\3\3\f\f\5\2\13\f\17\17\"\"\5\2\62;CHch\6\2\f\f$$))^^\7\2"
            + "$$))^^ppvv\3\2))\4\2$$))\4\2>>@@\3\2\62;\4\2C\\c|\4\2CHch\u0193\2\3\3"
            + "\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"
            + "\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"
            + "\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"
            + "%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"
            + "\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2"
            + "\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I"
            + "\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2"
            + "\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\3k\3\2\2\2\5y\3\2\2\2\7}\3\2\2\2"
            + "\t\u0088\3\2\2\2\13\u008b\3\2\2\2\r\u0093\3\2\2\2\17\u00ac\3\2\2\2\21"
            + "\u00ae\3\2\2\2\23\u00bb\3\2\2\2\25\u00ce\3\2\2\2\27\u00d0\3\2\2\2\31\u00d4"
            + "\3\2\2\2\33\u00db\3\2\2\2\35\u00e1\3\2\2\2\37\u00e5\3\2\2\2!\u00e7\3\2"
            + "\2\2#\u00eb\3\2\2\2%\u00f3\3\2\2\2\'\u00fb\3\2\2\2)\u0100\3\2\2\2+\u0103"
            + "\3\2\2\2-\u0108\3\2\2\2/\u010c\3\2\2\2\61\u0112\3\2\2\2\63\u0119\3\2\2"
            + "\2\65\u011f\3\2\2\2\67\u0128\3\2\2\29\u012d\3\2\2\2;\u0135\3\2\2\2=\u013e"
            + "\3\2\2\2?\u0140\3\2\2\2A\u0142\3\2\2\2C\u0144\3\2\2\2E\u0146\3\2\2\2G"
            + "\u0148\3\2\2\2I\u014a\3\2\2\2K\u014c\3\2\2\2M\u014e\3\2\2\2O\u0150\3\2"
            + "\2\2Q\u0152\3\2\2\2S\u0154\3\2\2\2U\u0157\3\2\2\2W\u015a\3\2\2\2Y\u015c"
            + "\3\2\2\2[\u015e\3\2\2\2]\u0160\3\2\2\2_\u0162\3\2\2\2a\u0166\3\2\2\2c"
            + "\u016a\3\2\2\2e\u016c\3\2\2\2g\u0172\3\2\2\2i\u0174\3\2\2\2kl\7\61\2\2"
            + "lm\7\61\2\2mq\3\2\2\2np\13\2\2\2on\3\2\2\2ps\3\2\2\2qr\3\2\2\2qo\3\2\2"
            + "\2ru\3\2\2\2sq\3\2\2\2tv\t\2\2\2ut\3\2\2\2vw\3\2\2\2wx\b\2\2\2x\4\3\2"
            + "\2\2yz\t\3\2\2z{\3\2\2\2{|\b\3\2\2|\6\3\2\2\2}~\7\62\2\2~\177\7z\2\2\177"
            + "\u0083\3\2\2\2\u0080\u0082\n\4\2\2\u0081\u0080\3\2\2\2\u0082\u0085\3\2"
            + "\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\b\3\2\2\2\u0085\u0083"
            + "\3\2\2\2\u0086\u0089\5\13\6\2\u0087\u0089\5i\65\2\u0088\u0086\3\2\2\2"
            + "\u0088\u0087\3\2\2\2\u0089\n\3\2\2\2\u008a\u008c\5]/\2\u008b\u008a\3\2"
            + "\2\2\u008c\u008d\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e"
            + "\f\3\2\2\2\u008f\u0090\7)\2\2\u0090\u0091\n\5\2\2\u0091\u0094\7)\2\2\u0092"
            + "\u0094\5e\63\2\u0093\u008f\3\2\2\2\u0093\u0092\3\2\2\2\u0094\16\3\2\2"
            + "\2\u0095\u0096\7)\2\2\u0096\u0097\t\5\2\2\u0097\u00ad\7)\2\2\u0098\u0099"
            + "\7)\2\2\u0099\u009a\7^\2\2\u009a\u009b\3\2\2\2\u009b\u009d\n\6\2\2\u009c"
            + "\u009e\7)\2\2\u009d\u009c\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u00ad\3\2"
            + "\2\2\u009f\u00a1\7)\2\2\u00a0\u00a2\n\7\2\2\u00a1\u00a0\3\2\2\2\u00a2"
            + "\u00a3\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\3\2"
            + "\2\2\u00a5\u00ad\7)\2\2\u00a6\u00a8\7)\2\2\u00a7\u00a9\n\7\2\2\u00a8\u00a7"
            + "\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab"
            + "\u00ad\3\2\2\2\u00ac\u0095\3\2\2\2\u00ac\u0098\3\2\2\2\u00ac\u009f\3\2"
            + "\2\2\u00ac\u00a6\3\2\2\2\u00ad\20\3\2\2\2\u00ae\u00b6\7$\2\2\u00af\u00b0"
            + "\7^\2\2\u00b0\u00b5\7$\2\2\u00b1\u00b2\7^\2\2\u00b2\u00b5\7)\2\2\u00b3"
            + "\u00b5\n\b\2\2\u00b4\u00af\3\2\2\2\u00b4\u00b1\3\2\2\2\u00b4\u00b3\3\2"
            + "\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7"
            + "\u00b9\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00ba\7$\2\2\u00ba\22\3\2\2\2"
            + "\u00bb\u00c3\7$\2\2\u00bc\u00bd\7^\2\2\u00bd\u00c2\7$\2\2\u00be\u00bf"
            + "\7^\2\2\u00bf\u00c2\7)\2\2\u00c0\u00c2\n\b\2\2\u00c1\u00bc\3\2\2\2\u00c1"
            + "\u00be\3\2\2\2\u00c1\u00c0\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2"
            + "\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6"
            + "\u00c8\7)\2\2\u00c7\u00c6\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\24\3\2\2\2"
            + "\u00c9\u00cf\5g\64\2\u00ca\u00cb\7-\2\2\u00cb\u00cf\7?\2\2\u00cc\u00cd"
            + "\7/\2\2\u00cd\u00cf\7?\2\2\u00ce\u00c9\3\2\2\2\u00ce\u00ca\3\2\2\2\u00ce"
            + "\u00cc\3\2\2\2\u00cf\26\3\2\2\2\u00d0\u00d1\7#\2\2\u00d1\30\3\2\2\2\u00d2"
            + "\u00d5\5U+\2\u00d3\u00d5\5S*\2\u00d4\u00d2\3\2\2\2\u00d4\u00d3\3\2\2\2"
            + "\u00d5\32\3\2\2\2\u00d6\u00dc\t\t\2\2\u00d7\u00d8\7>\2\2\u00d8\u00dc\7"
            + "?\2\2\u00d9\u00da\7@\2\2\u00da\u00dc\7?\2\2\u00db\u00d6\3\2\2\2\u00db"
            + "\u00d7\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc\34\3\2\2\2\u00dd\u00de\7?\2\2"
            + "\u00de\u00e2\7?\2\2\u00df\u00e0\7#\2\2\u00e0\u00e2\7?\2\2\u00e1\u00dd"
            + "\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\36\3\2\2\2\u00e3\u00e6\5\67\34\2\u00e4"
            + "\u00e6\59\35\2\u00e5\u00e3\3\2\2\2\u00e5\u00e4\3\2\2\2\u00e6 \3\2\2\2"
            + "\u00e7\u00e8\7k\2\2\u00e8\u00e9\7p\2\2\u00e9\u00ea\7v\2\2\u00ea\"\3\2"
            + "\2\2\u00eb\u00ec\7d\2\2\u00ec\u00ed\7q\2\2\u00ed\u00ee\7q\2\2\u00ee\u00ef"
            + "\7n\2\2\u00ef\u00f0\7g\2\2\u00f0\u00f1\7c\2\2\u00f1\u00f2\7p\2\2\u00f2"
            + "$\3\2\2\2\u00f3\u00f4\7e\2\2\u00f4\u00f5\7c\2\2\u00f5\u00f6\7n\2\2\u00f6"
            + "\u00f7\7n\2\2\u00f7\u00f8\7q\2\2\u00f8\u00f9\7w\2\2\u00f9\u00fa\7v\2\2"
            + "\u00fa&\3\2\2\2\u00fb\u00fc\7x\2\2\u00fc\u00fd\7q\2\2\u00fd\u00fe\7k\2"
            + "\2\u00fe\u00ff\7f\2\2\u00ff(\3\2\2\2\u0100\u0101\7k\2\2\u0101\u0102\7"
            + "h\2\2\u0102*\3\2\2\2\u0103\u0104\7g\2\2\u0104\u0105\7n\2\2\u0105\u0106"
            + "\7u\2\2\u0106\u0107\7g\2\2\u0107,\3\2\2\2\u0108\u0109\7h\2\2\u0109\u010a"
            + "\7q\2\2\u010a\u010b\7t\2\2\u010b.\3\2\2\2\u010c\u010d\7y\2\2\u010d\u010e"
            + "\7j\2\2\u010e\u010f\7k\2\2\u010f\u0110\7n\2\2\u0110\u0111\7g\2\2\u0111"
            + "\60\3\2\2\2\u0112\u0113\7t\2\2\u0113\u0114\7g\2\2\u0114\u0115\7v\2\2\u0115"
            + "\u0116\7w\2\2\u0116\u0117\7t\2\2\u0117\u0118\7p\2\2\u0118\62\3\2\2\2\u0119"
            + "\u011a\7d\2\2\u011a\u011b\7t\2\2\u011b\u011c\7g\2\2\u011c\u011d\7c\2\2"
            + "\u011d\u011e\7m\2\2\u011e\64\3\2\2\2\u011f\u0120\7e\2\2\u0120\u0121\7"
            + "q\2\2\u0121\u0122\7p\2\2\u0122\u0123\7v\2\2\u0123\u0124\7k\2\2\u0124\u0125"
            + "\7p\2\2\u0125\u0126\7w\2\2\u0126\u0127\7g\2\2\u0127\66\3\2\2\2\u0128\u0129"
            + "\7v\2\2\u0129\u012a\7t\2\2\u012a\u012b\7w\2\2\u012b\u012c\7g\2\2\u012c"
            + "8\3\2\2\2\u012d\u012e\7h\2\2\u012e\u012f\7c\2\2\u012f\u0130\7n\2\2\u0130"
            + "\u0131\7u\2\2\u0131\u0132\7g\2\2\u0132:\3\2\2\2\u0133\u0136\5_\60\2\u0134"
            + "\u0136\7a\2\2\u0135\u0133\3\2\2\2\u0135\u0134\3\2\2\2\u0136\u013b\3\2"
            + "\2\2\u0137\u013a\5a\61\2\u0138\u013a\7a\2\2\u0139\u0137\3\2\2\2\u0139"
            + "\u0138\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2"
            + "\2\2\u013c<\3\2\2\2\u013d\u013b\3\2\2\2\u013e\u013f\7*\2\2\u013f>\3\2"
            + "\2\2\u0140\u0141\7+\2\2\u0141@\3\2\2\2\u0142\u0143\7]\2\2\u0143B\3\2\2"
            + "\2\u0144\u0145\7_\2\2\u0145D\3\2\2\2\u0146\u0147\7}\2\2\u0147F\3\2\2\2"
            + "\u0148\u0149\7\177\2\2\u0149H\3\2\2\2\u014a\u014b\7-\2\2\u014bJ\3\2\2"
            + "\2\u014c\u014d\7/\2\2\u014dL\3\2\2\2\u014e\u014f\7,\2\2\u014fN\3\2\2\2"
            + "\u0150\u0151\7\61\2\2\u0151P\3\2\2\2\u0152\u0153\7\'\2\2\u0153R\3\2\2"
            + "\2\u0154\u0155\7(\2\2\u0155\u0156\7(\2\2\u0156T\3\2\2\2\u0157\u0158\7"
            + "~\2\2\u0158\u0159\7~\2\2\u0159V\3\2\2\2\u015a\u015b\7=\2\2\u015bX\3\2"
            + "\2\2\u015c\u015d\7.\2\2\u015dZ\3\2\2\2\u015e\u015f\13\2\2\2\u015f\\\3"
            + "\2\2\2\u0160\u0161\t\n\2\2\u0161^\3\2\2\2\u0162\u0163\t\13\2\2\u0163`"
            + "\3\2\2\2\u0164\u0167\5]/\2\u0165\u0167\5_\60\2\u0166\u0164\3\2\2\2\u0166"
            + "\u0165\3\2\2\2\u0167b\3\2\2\2\u0168\u016b\5]/\2\u0169\u016b\t\f\2\2\u016a"
            + "\u0168\3\2\2\2\u016a\u0169\3\2\2\2\u016bd\3\2\2\2\u016c\u016d\7)\2\2\u016d"
            + "\u016e\7^\2\2\u016e\u016f\3\2\2\2\u016f\u0170\t\6\2\2\u0170\u0171\7)\2"
            + "\2\u0171f\3\2\2\2\u0172\u0173\7?\2\2\u0173h\3\2\2\2\u0174\u0175\7\62\2"
            + "\2\u0175\u0176\7z\2\2\u0176\u0178\3\2\2\2\u0177\u0179\5c\62\2\u0178\u0177"
            + "\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u0178\3\2\2\2\u017a\u017b\3\2\2\2\u017b"
            + "j\3\2\2\2\35\2qu\u0083\u0088\u008d\u0093\u009d\u00a3\u00aa\u00ac\u00b4"
            + "\u00b6\u00c1\u00c3\u00c7\u00ce\u00d4\u00db\u00e1\u00e5\u0135\u0139\u013b"
            + "\u0166\u016a\u017a\3\b\2\2";
    public static final ATN _ATN
            = new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}
