// Generated from Calculette.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalculetteParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, NEWLINE=31, 
		WS=32, ENTIER=33, FLOAT=34, TYPE=35, RETURN=36, IDENTIFIANT=37, LONG_COMMENT=38, 
		SHORT_COMMENT=39;
	public static final int
		RULE_start = 0, RULE_calcul = 1, RULE_instruction = 2, RULE_expression = 3, 
		RULE_finInstruction = 4, RULE_decl = 5, RULE_assignation = 6, RULE_input = 7, 
		RULE_boucle = 8, RULE_condition = 9, RULE_bloc = 10, RULE_codeif = 11, 
		RULE_codefor = 12, RULE_coderepeat = 13, RULE_fonction = 14, RULE_params = 15, 
		RULE_args = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "calcul", "instruction", "expression", "finInstruction", "decl", 
			"assignation", "input", "boucle", "condition", "bloc", "codeif", "codefor", 
			"coderepeat", "fonction", "params", "args"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'*'", "'/'", "'+'", "'-'", "';'", "'='", "'read('", 
			"'write('", "'while'", "'&&'", "'||'", "'!'", "'=='", "'!='", "'>'", 
			"'>='", "'<'", "'<='", "'true'", "'false'", "'{'", "'}'", "'if'", "'else'", 
			"'for'", "'repeat'", "'until'", "','", null, null, null, null, null, 
			"'return'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "NEWLINE", "WS", "ENTIER", 
			"FLOAT", "TYPE", "RETURN", "IDENTIFIANT", "LONG_COMMENT", "SHORT_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Calculette.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



	    private TablesSymboles tablesSymboles = new TablesSymboles();

	    private String mvapEval(String op) {
	        if ( op.equals("*") ){
	            return "MUL";
	        } else if ( op.equals("+") ){
	            return "ADD";
	        } else if (op.equals("-")) {
	            return "SUB";
	        } else if (op.equals("/")) {
	            return "DIV";
	        } else {
	           System.err.println("Opérateur arithmétique incorrect : '"+op+"'");
	           throw new IllegalArgumentException("Opérateur arithmétique incorrect : '"+op+"'");
	        }
	    }

	    private int _cur_label = 1;
	    /** générateur de nom d'étiquettes pour les boucles */
	    private String getNewLabel() { return "B" +(_cur_label++); }

	    private String evalboucle(String cond, String inst) {
	        String labelFin = getNewLabel();
	        String labelDebut = getNewLabel();
	        String sret = "";
	        sret += "LABEL " + labelDebut + "\n";
	        sret += cond;
	        sret += "JUMPF " + labelFin + "\n";
	        sret += inst;
	        sret += "JUMP " + labelDebut + "\n";
	        sret += "LABEL " + labelFin + "\n";
	        return sret;
	    }

	    private String evalCond(String x, String op, String y) {
	        if (op.equals("==")) {
	            return x + y + "EQUAL\n";
	        } else if (op.equals("<")) {
	            return x + y + "INF\n";
	        } else if (op.equals(">")) {
	            return x + y + "SUP\n";
	        } else if (op.equals("!=")) {
	            return x + y + "NEQ\n";
	        } else if (op.equals("<=")) {
	            return x + y + "INFEQ\n";
	        } else if (op.equals(">=")) {
	            return x + y + "SUPEQ\n";
	        } else {
	            System.err.println("Opérateur de comparaison incorrect : '" + op + "'");
	            throw new IllegalArgumentException("Opérateur de comparaison incorrect : '" + op + "'");
	        }
	    }

	    private String evallog(String op) {
	        if (op.equals("&&")) {
	            return "MUL\n";
	        } else if (op.equals("||")) {
	            return "ADD\n";
	        } else if (op.equals("!")) {
	            return "PUSHI 0\nEQUAL\n";
	        } else {
	            System.err.println("Opérateur logique incorrect : '" + op + "'");
	            throw new IllegalArgumentException("Opérateur logique incorrect : '" + op + "'");
	        }
	    }

	    private String evalif(String cond, String instr) {
	        String labelDebut = getNewLabel();
	        String sret = "";
	        sret += cond;
	        sret += "JUMPF " + labelDebut + "\n";
	        sret += instr;
	        sret += "LABEL " + labelDebut + "\n";
	        return sret;
	    }

	    private String evalifelse(String cond, String instr, String instrelse) {
	        String labelFin = getNewLabel();
	        String labelDebutElse = getNewLabel();
	        String sret = "";
	        sret += cond;
	        sret += "JUMPF " + labelDebutElse + "\n";
	        sret += instr;
	        sret += "JUMP " + labelFin + "\n";
	        sret += "LABEL " + labelDebutElse + "\n";
	        sret += instrelse;
	        sret += "JUMP " + labelFin + "\n";
	        sret += "LABEL " + labelFin + "\n";
	        return sret;
	    }

	    private String evalfor(String assignation, String cond, String increment, String instr) {
	        String labelDebutFor = getNewLabel();
	        String labelFinFor = getNewLabel();
	        String sret = "";
	        sret += assignation;
	        sret += "LABEL " + labelDebutFor + "\n";
	        sret += cond;
	        sret += "JUMPF " + labelFinFor + "\n";
	        sret += instr;
	        sret += increment;
	        sret += "JUMP " + labelDebutFor + "\n";
	        sret += "LABEL " + labelFinFor + "\n";
	        return sret;
	    }

	    private String evalRepeat(String instruction, String condition) {
	        String labelDebut = getNewLabel();
	        String sret = "";
	        sret += "LABEL " + labelDebut + "\n";
	        sret += instruction;
	        sret += condition;
	        sret += "JUMPF " + labelDebut + "\n";
	        return sret;
	    }

	public CalculetteParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public CalculContext calcul() {
			return getRuleContext(CalculContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CalculetteParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			calcul();
			setState(35);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CalculContext extends ParserRuleContext {
		public String code;
		public DeclContext decl;
		public FonctionContext fonction;
		public InstructionContext instruction;
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public List<FonctionContext> fonction() {
			return getRuleContexts(FonctionContext.class);
		}
		public FonctionContext fonction(int i) {
			return getRuleContext(FonctionContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public CalculContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calcul; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCalcul(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCalcul(this);
		}
	}

	public final CalculContext calcul() throws RecognitionException {
		CalculContext _localctx = new CalculContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_calcul);
		 ((CalculContext)_localctx).code =  new String(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(37);
					((CalculContext)_localctx).decl = decl();
					 _localctx.code += ((CalculContext)_localctx).decl.code; 
					}
					} 
				}
				setState(44);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			 _localctx.code += "JUMP Main\n"; 
			setState(49);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(46);
					match(NEWLINE);
					}
					} 
				}
				setState(51);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE) {
				{
				{
				setState(52);
				((CalculContext)_localctx).fonction = fonction();
				 _localctx.code += ((CalculContext)_localctx).fonction.code; 
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(60);
					match(NEWLINE);
					}
					} 
				}
				setState(65);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			 _localctx.code += "LABEL Main\n"; 
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__22) | (1L << T__24) | (1L << T__26) | (1L << T__27) | (1L << NEWLINE) | (1L << ENTIER) | (1L << FLOAT) | (1L << RETURN) | (1L << IDENTIFIANT))) != 0)) {
				{
				{
				setState(67);
				((CalculContext)_localctx).instruction = instruction();
				 _localctx.code += ((CalculContext)_localctx).instruction.code; 
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			 _localctx.code += "  HALT\n"; 
			}
			_ctx.stop = _input.LT(-1);
			 System.out.println(_localctx.code); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionContext extends ParserRuleContext {
		public String code;
		public BlocContext bloc;
		public ExpressionContext expression;
		public AssignationContext assignation;
		public InputContext input;
		public BoucleContext boucle;
		public CodeifContext codeif;
		public CodeforContext codefor;
		public CoderepeatContext coderepeat;
		public BlocContext bloc() {
			return getRuleContext(BlocContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public AssignationContext assignation() {
			return getRuleContext(AssignationContext.class,0);
		}
		public InputContext input() {
			return getRuleContext(InputContext.class,0);
		}
		public BoucleContext boucle() {
			return getRuleContext(BoucleContext.class,0);
		}
		public CodeifContext codeif() {
			return getRuleContext(CodeifContext.class,0);
		}
		public CodeforContext codefor() {
			return getRuleContext(CodeforContext.class,0);
		}
		public CoderepeatContext coderepeat() {
			return getRuleContext(CoderepeatContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(CalculetteParser.RETURN, 0); }
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitInstruction(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instruction);
		try {
			setState(112);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				((InstructionContext)_localctx).bloc = bloc();

				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).bloc.code;
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				((InstructionContext)_localctx).expression = expression(0);
				setState(81);
				finInstruction();
				 
				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).expression.code;
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(84);
				((InstructionContext)_localctx).assignation = assignation();
				setState(85);
				finInstruction();

				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).assignation.code;
				    
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(88);
				((InstructionContext)_localctx).input = input();
				setState(89);
				finInstruction();

				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).input.code;
				    
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(92);
				((InstructionContext)_localctx).boucle = boucle();

				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).boucle.code;
				    
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(95);
				((InstructionContext)_localctx).codeif = codeif();

				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).codeif.code;
				    
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(98);
				((InstructionContext)_localctx).codefor = codefor();

				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).codefor.code;
				    
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(101);
				((InstructionContext)_localctx).coderepeat = coderepeat();

				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).coderepeat.code;
				    
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(104);
				match(RETURN);
				setState(105);
				((InstructionContext)_localctx).expression = expression(0);
				setState(106);
				finInstruction();

				        AdresseType at = tablesSymboles.getAdresseType("return");
				        ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).expression.code;
				        

				        // Tester si entier ou float, si float : STOREL de deux valeurs (décimal et entière) et donc deux return
				        if(at.type.equals("float")){
				           _localctx.code += "STOREL " + at.adresse + "\n";
				           _localctx.code += "STOREL " + ( at.adresse + 1 ) + "\nRETURN\nRETURN\n" ;
				        }
				        else{
				            _localctx.code += "STOREL " + at.adresse + "\nRETURN\n";
				        }
				    
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(109);
				finInstruction();

				        ((InstructionContext)_localctx).code = "";
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public String code;
		public String type;
		public ExpressionContext a;
		public ExpressionContext expression;
		public Token ENTIER;
		public Token FLOAT;
		public Token IDENTIFIANT;
		public ArgsContext args;
		public Token op;
		public ExpressionContext b;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ENTIER() { return getToken(CalculetteParser.ENTIER, 0); }
		public TerminalNode FLOAT() { return getToken(CalculetteParser.FLOAT, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(115);
				match(T__0);
				setState(116);
				((ExpressionContext)_localctx).a = ((ExpressionContext)_localctx).expression = expression(0);
				setState(117);
				match(T__1);
				 ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code; ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).expression.type; 
				}
				break;
			case 2:
				{
				setState(120);
				((ExpressionContext)_localctx).ENTIER = match(ENTIER);
				 ((ExpressionContext)_localctx).code =  "PUSHI " + (((ExpressionContext)_localctx).ENTIER!=null?((ExpressionContext)_localctx).ENTIER.getText():null) + "\n"; ((ExpressionContext)_localctx).type =  "int"; 
				}
				break;
			case 3:
				{
				setState(122);
				match(T__5);
				setState(123);
				((ExpressionContext)_localctx).ENTIER = match(ENTIER);
				 ((ExpressionContext)_localctx).code =  "PUSHI -" + (((ExpressionContext)_localctx).ENTIER!=null?((ExpressionContext)_localctx).ENTIER.getText():null) + "\n"; ((ExpressionContext)_localctx).type =  "int"; 
				}
				break;
			case 4:
				{
				setState(125);
				((ExpressionContext)_localctx).FLOAT = match(FLOAT);
				 ((ExpressionContext)_localctx).code =  "PUSHF " + (((ExpressionContext)_localctx).FLOAT!=null?((ExpressionContext)_localctx).FLOAT.getText():null) + "\n"; ((ExpressionContext)_localctx).type =  "float"; 
				}
				break;
			case 5:
				{
				setState(127);
				match(T__5);
				setState(128);
				((ExpressionContext)_localctx).FLOAT = match(FLOAT);
				 ((ExpressionContext)_localctx).code =  "PUSHF -" + (((ExpressionContext)_localctx).FLOAT!=null?((ExpressionContext)_localctx).FLOAT.getText():null) + "\n"; ((ExpressionContext)_localctx).type =  "float"; 
				}
				break;
			case 6:
				{
				setState(130);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(131);
				match(T__0);
				setState(132);
				((ExpressionContext)_localctx).args = args();
				setState(133);
				match(T__1);

				        ((ExpressionContext)_localctx).type =  tablesSymboles.getFunction((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null));
				        // Pour les double, pushi 0.0
				        if(_localctx.type.equals("float")){
				            ((ExpressionContext)_localctx).code =  "PUSHF 0.0\n";
				        }
				        else{
				            ((ExpressionContext)_localctx).code =  "PUSHI 0\n";
				        }
				        
				        _localctx.code += ((ExpressionContext)_localctx).args.code;
				        _localctx.code += "CALL " + (((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null) + "\n";
				        for (int i = 0; i < ((ExpressionContext)_localctx).args.size; i++) {
				            _localctx.code += "POP\n";
				        }
				    
				}
				break;
			case 7:
				{
				setState(136);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

				            AdresseType at = tablesSymboles.getAdresseType((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null));
				            if (at.adresse > 0) {
				                if(at.type.equals("float")){
				                     ((ExpressionContext)_localctx).code =  "PUSHG " + at.adresse + "\n";
				                     ((ExpressionContext)_localctx).code =  "PUSHG " + (at.adresse + 1) + "\n";
				               }
				                else{
				                ((ExpressionContext)_localctx).code =  "PUSHG " + at.adresse + "\n";
				                }
				            } else {
				                if(at.type.equals("float")){
				                    ((ExpressionContext)_localctx).code =  "PUSHL " + at.adresse + "\n";
				                    ((ExpressionContext)_localctx).code =  "PUSHL " + ( at.adresse + 1 ) + "\n";
				                }
				                else{
				                    ((ExpressionContext)_localctx).code =  "PUSHL " + at.adresse + "\n";
				                }
				                
				            }
				            ((ExpressionContext)_localctx).type =  at.type;
				        
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(152);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(150);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(140);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(141);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__2 || _la==T__3) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(142);
						((ExpressionContext)_localctx).b = ((ExpressionContext)_localctx).expression = expression(9);
						 ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + ((ExpressionContext)_localctx).b.code + mvapEval((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)) + "\n"; ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).expression.type; 
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(145);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(146);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__4 || _la==T__5) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(147);
						((ExpressionContext)_localctx).b = ((ExpressionContext)_localctx).expression = expression(8);
						 ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + ((ExpressionContext)_localctx).b.code + mvapEval((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)) + "\n"; ((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).expression.type; 
						}
						break;
					}
					} 
				}
				setState(154);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FinInstructionContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public FinInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterFinInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitFinInstruction(this);
		}
	}

	public final FinInstructionContext finInstruction() throws RecognitionException {
		FinInstructionContext _localctx = new FinInstructionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_finInstruction);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(156); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(155);
					_la = _input.LA(1);
					if ( !(_la==T__6 || _la==NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(158); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public String code;
		public Token TYPE;
		public Token IDENTIFIANT;
		public Token ENTIER;
		public Token FLOAT;
		public TerminalNode TYPE() { return getToken(CalculetteParser.TYPE, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public TerminalNode ENTIER() { return getToken(CalculetteParser.ENTIER, 0); }
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public TerminalNode FLOAT() { return getToken(CalculetteParser.FLOAT, 0); }
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_decl);
		try {
			setState(179);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				((DeclContext)_localctx).TYPE = match(TYPE);
				setState(161);
				((DeclContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(162);
				match(T__7);
				setState(163);
				((DeclContext)_localctx).ENTIER = match(ENTIER);
				setState(164);
				finInstruction();

				            tablesSymboles.putVar((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null), (((DeclContext)_localctx).TYPE!=null?((DeclContext)_localctx).TYPE.getText():null));
				            ((DeclContext)_localctx).code =  "PUSHI 0\n";
				            _localctx.code += "PUSHI " + (((DeclContext)_localctx).ENTIER!=null?((DeclContext)_localctx).ENTIER.getText():null) + "\n";
				            AdresseType at = tablesSymboles.getAdresseType((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null));
				            if (at.adresse < 0) {
				                _localctx.code += "STOREL " + at.adresse + "\n";
				            } else {
				                _localctx.code += "STOREG " + at.adresse + "\n";
				            }
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(167);
				((DeclContext)_localctx).TYPE = match(TYPE);
				setState(168);
				((DeclContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(169);
				finInstruction();

				            tablesSymboles.putVar((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null), (((DeclContext)_localctx).TYPE!=null?((DeclContext)_localctx).TYPE.getText():null));
				            ((DeclContext)_localctx).code =  "PUSHI 0\n";
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(172);
				((DeclContext)_localctx).TYPE = match(TYPE);
				setState(173);
				((DeclContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(174);
				match(T__7);
				setState(175);
				((DeclContext)_localctx).FLOAT = match(FLOAT);
				setState(176);
				finInstruction();

				            tablesSymboles.putVar((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null), (((DeclContext)_localctx).TYPE!=null?((DeclContext)_localctx).TYPE.getText():null));
				            ((DeclContext)_localctx).code =  "PUSHI 0\n";
				            _localctx.code += "PUSHI " + (((DeclContext)_localctx).FLOAT!=null?((DeclContext)_localctx).FLOAT.getText():null) + "\n";
				            AdresseType at = tablesSymboles.getAdresseType((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null));
				            if (at.adresse < 0) {
				                _localctx.code += "STOREL " + at.adresse + "\n";
				            } else {
				                _localctx.code += "STOREG " + at.adresse + "\n";
				            }
				        
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignationContext extends ParserRuleContext {
		public String code;
		public Token IDENTIFIANT;
		public ExpressionContext a;
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterAssignation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitAssignation(this);
		}
	}

	public final AssignationContext assignation() throws RecognitionException {
		AssignationContext _localctx = new AssignationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assignation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			((AssignationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
			setState(182);
			match(T__7);
			setState(183);
			((AssignationContext)_localctx).a = expression(0);

			            AdresseType at = tablesSymboles.getAdresseType((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null));
			            if (at.adresse < 0) {
			                if(at.type.equals("float")){
			                    ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).a.code + "STOREL " + at.adresse + "\n";
			                    ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).a.code + "STOREL " + (at.adresse + 1 ) + "\n";
			                }
			                else{
			                    ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).a.code + "STOREL " + at.adresse + "\n";
			                }
			            } else {
			                if(at.type.equals("float")){
			                    ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).a.code + "STOREG " + at.adresse + "\n";
			                    ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).a.code + "STOREG " + ( at.adresse + 1 ) + "\n";
			                }
			                else{
			                    ((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).a.code + "STOREG " + at.adresse + "\n";
			                }
			            }
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InputContext extends ParserRuleContext {
		public String code;
		public Token IDENTIFIANT;
		public ExpressionContext a;
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public InputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitInput(this);
		}
	}

	public final InputContext input() throws RecognitionException {
		InputContext _localctx = new InputContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_input);
		try {
			setState(195);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(186);
				match(T__8);
				setState(187);
				((InputContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(188);
				match(T__1);

				            ((InputContext)_localctx).code =  "READ\n";
				            AdresseType at = tablesSymboles.getAdresseType((((InputContext)_localctx).IDENTIFIANT!=null?((InputContext)_localctx).IDENTIFIANT.getText():null));
				            if (at.adresse < 0) {
				                if(at.type.equals("float")){
				                    _localctx.code += "STOREL" + at.adresse + "\n";
				                    _localctx.code += "STOREL" + ( at.adresse + 1 ) + "\n";
				                }
				                else{
				                    _localctx.code += "STOREL" + at.adresse + "\n";
				                }
				            } else {
				                if(at.type.equals("float")){
				                    _localctx.code += "STOREG " + at.adresse + "\n";
				                    _localctx.code += "STOREG " + ( at.adresse + 1 ) + "\n";
				                }
				                else{
				                    _localctx.code += "STOREG " + at.adresse + "\n";
				                }
				                
				            }
				        
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(190);
				match(T__9);
				setState(191);
				((InputContext)_localctx).a = expression(0);
				setState(192);
				match(T__1);

				            ((InputContext)_localctx).code =  ((InputContext)_localctx).a.code;
				            _localctx.code += "WRITE\n";
				            _localctx.code += "POP\n";

				            if(((InputContext)_localctx).a.type.equals("foat")){
				                _localctx.code += "POP\n";
				            }
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoucleContext extends ParserRuleContext {
		public String code;
		public ConditionContext a;
		public InstructionContext b;
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public BoucleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boucle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterBoucle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitBoucle(this);
		}
	}

	public final BoucleContext boucle() throws RecognitionException {
		BoucleContext _localctx = new BoucleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_boucle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(T__10);
			setState(198);
			match(T__0);
			setState(199);
			((BoucleContext)_localctx).a = condition(0);
			setState(200);
			match(T__1);
			setState(201);
			((BoucleContext)_localctx).b = instruction();

			        ((BoucleContext)_localctx).code =  evalboucle(((BoucleContext)_localctx).a.code, ((BoucleContext)_localctx).b.code);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public String code;
		public ConditionContext c;
		public ExpressionContext a;
		public Token op;
		public ExpressionContext b;
		public ConditionContext d;
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		return condition(0);
	}

	private ConditionContext condition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionContext _localctx = new ConditionContext(_ctx, _parentState);
		ConditionContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_condition, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
				{
				setState(205);
				match(T__13);
				setState(206);
				((ConditionContext)_localctx).c = condition(4);

				        ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).c.code + evallog("!");
				    
				}
				break;
			case T__0:
			case T__5:
			case ENTIER:
			case FLOAT:
			case IDENTIFIANT:
				{
				setState(209);
				((ConditionContext)_localctx).a = expression(0);
				setState(210);
				((ConditionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) ) {
					((ConditionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(211);
				((ConditionContext)_localctx).b = expression(0);

				        ((ConditionContext)_localctx).code =  evalCond(((ConditionContext)_localctx).a.code, (((ConditionContext)_localctx).op!=null?((ConditionContext)_localctx).op.getText():null), ((ConditionContext)_localctx).b.code);
				    
				}
				break;
			case T__20:
				{
				setState(214);
				match(T__20);
				 ((ConditionContext)_localctx).code =  "PUSHI 1\n"; 
				}
				break;
			case T__21:
				{
				setState(216);
				match(T__21);
				 ((ConditionContext)_localctx).code =  "PUSHI 0\n"; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(232);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(230);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						_localctx.c = _prevctx;
						_localctx.c = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(220);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(221);
						match(T__11);
						setState(222);
						((ConditionContext)_localctx).d = condition(7);

						                  ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).c.code + ((ConditionContext)_localctx).d.code + evallog("&&");
						              
						}
						break;
					case 2:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						_localctx.c = _prevctx;
						_localctx.c = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(225);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(226);
						match(T__12);
						setState(227);
						((ConditionContext)_localctx).d = condition(6);

						                  ((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).c.code + ((ConditionContext)_localctx).d.code +evallog("||");
						              
						}
						break;
					}
					} 
				}
				setState(234);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BlocContext extends ParserRuleContext {
		public String code;
		public InstructionContext instruction;
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public BlocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterBloc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitBloc(this);
		}
	}

	public final BlocContext bloc() throws RecognitionException {
		BlocContext _localctx = new BlocContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_bloc);
		 ((BlocContext)_localctx).code =  "";
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(T__22);
			setState(239); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(236);
				((BlocContext)_localctx).instruction = instruction();
				 _localctx.code += ((BlocContext)_localctx).instruction.code;
				}
				}
				setState(241); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__22) | (1L << T__24) | (1L << T__26) | (1L << T__27) | (1L << NEWLINE) | (1L << ENTIER) | (1L << FLOAT) | (1L << RETURN) | (1L << IDENTIFIANT))) != 0) );
			setState(243);
			match(T__23);
			setState(247);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(244);
					match(NEWLINE);
					}
					} 
				}
				setState(249);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CodeifContext extends ParserRuleContext {
		public String code;
		public ConditionContext a;
		public InstructionContext b;
		public InstructionContext c;
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public CodeifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeif; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCodeif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCodeif(this);
		}
	}

	public final CodeifContext codeif() throws RecognitionException {
		CodeifContext _localctx = new CodeifContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_codeif);
		try {
			setState(266);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(250);
				match(T__24);
				setState(251);
				match(T__0);
				setState(252);
				((CodeifContext)_localctx).a = condition(0);
				setState(253);
				match(T__1);
				setState(254);
				((CodeifContext)_localctx).b = instruction();


				        ((CodeifContext)_localctx).code =  evalif(((CodeifContext)_localctx).a.code, ((CodeifContext)_localctx).b.code);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(257);
				match(T__24);
				setState(258);
				match(T__0);
				setState(259);
				((CodeifContext)_localctx).a = condition(0);
				setState(260);
				match(T__1);
				setState(261);
				((CodeifContext)_localctx).b = instruction();
				setState(262);
				match(T__25);
				setState(263);
				((CodeifContext)_localctx).c = instruction();

				        ((CodeifContext)_localctx).code =  evalifelse(((CodeifContext)_localctx).a.code, ((CodeifContext)_localctx).b.code, ((CodeifContext)_localctx).c.code);
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CodeforContext extends ParserRuleContext {
		public String code;
		public AssignationContext a;
		public ConditionContext c;
		public AssignationContext b;
		public InstructionContext i;
		public List<AssignationContext> assignation() {
			return getRuleContexts(AssignationContext.class);
		}
		public AssignationContext assignation(int i) {
			return getRuleContext(AssignationContext.class,i);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public CodeforContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codefor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCodefor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCodefor(this);
		}
	}

	public final CodeforContext codefor() throws RecognitionException {
		CodeforContext _localctx = new CodeforContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_codefor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			match(T__26);
			setState(269);
			match(T__0);
			setState(270);
			((CodeforContext)_localctx).a = assignation();
			setState(271);
			match(T__6);
			setState(272);
			((CodeforContext)_localctx).c = condition(0);
			setState(273);
			match(T__6);
			setState(274);
			((CodeforContext)_localctx).b = assignation();
			setState(275);
			match(T__1);
			setState(276);
			((CodeforContext)_localctx).i = instruction();

			        ((CodeforContext)_localctx).code =  evalfor(((CodeforContext)_localctx).a.code, ((CodeforContext)_localctx).c.code, ((CodeforContext)_localctx).b.code, ((CodeforContext)_localctx).i.code);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CoderepeatContext extends ParserRuleContext {
		public String code;
		public InstructionContext i;
		public ConditionContext c;
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public CoderepeatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coderepeat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCoderepeat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCoderepeat(this);
		}
	}

	public final CoderepeatContext coderepeat() throws RecognitionException {
		CoderepeatContext _localctx = new CoderepeatContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_coderepeat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			match(T__27);
			setState(280);
			((CoderepeatContext)_localctx).i = instruction();
			setState(281);
			match(T__28);
			setState(282);
			match(T__0);
			setState(283);
			((CoderepeatContext)_localctx).c = condition(0);
			setState(284);
			match(T__1);

			        ((CoderepeatContext)_localctx).code =  evalRepeat(((CoderepeatContext)_localctx).i.code, ((CoderepeatContext)_localctx).c.code);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FonctionContext extends ParserRuleContext {
		public String code;
		public Token TYPE;
		public Token IDENTIFIANT;
		public BlocContext bloc;
		public TerminalNode TYPE() { return getToken(CalculetteParser.TYPE, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public BlocContext bloc() {
			return getRuleContext(BlocContext.class,0);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public FonctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fonction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterFonction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitFonction(this);
		}
	}

	public final FonctionContext fonction() throws RecognitionException {
		FonctionContext _localctx = new FonctionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_fonction);
		 tablesSymboles.newTableLocale(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			((FonctionContext)_localctx).TYPE = match(TYPE);

			            tablesSymboles.putVar("return", (((FonctionContext)_localctx).TYPE!=null?((FonctionContext)_localctx).TYPE.getText():null));
			            // code java pour gérer la déclaration de "la variable" de retour de la fonction
			        
			setState(289);
			((FonctionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
			setState(290);
			match(T__0);
			setState(292);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TYPE) {
				{
				setState(291);
				params();
				}
			}

			setState(294);
			match(T__1);

			            ((FonctionContext)_localctx).code =  "LABEL " + (((FonctionContext)_localctx).IDENTIFIANT!=null?((FonctionContext)_localctx).IDENTIFIANT.getText():null) + "\n";
			            tablesSymboles.newFunction((((FonctionContext)_localctx).IDENTIFIANT!=null?((FonctionContext)_localctx).IDENTIFIANT.getText():null), (((FonctionContext)_localctx).TYPE!=null?((FonctionContext)_localctx).TYPE.getText():null));
			        
			setState(296);
			((FonctionContext)_localctx).bloc = bloc();

			            _localctx.code += ((FonctionContext)_localctx).bloc.code;
			            _localctx.code += "RETURN\n";            
			        
			}
			_ctx.stop = _input.LT(-1);
			 tablesSymboles.dropTableLocale(); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public Token TYPE;
		public Token IDENTIFIANT;
		public List<TerminalNode> TYPE() { return getTokens(CalculetteParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(CalculetteParser.TYPE, i);
		}
		public List<TerminalNode> IDENTIFIANT() { return getTokens(CalculetteParser.IDENTIFIANT); }
		public TerminalNode IDENTIFIANT(int i) {
			return getToken(CalculetteParser.IDENTIFIANT, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			((ParamsContext)_localctx).TYPE = match(TYPE);
			setState(300);
			((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

			            tablesSymboles.putVar((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null), (((ParamsContext)_localctx).TYPE!=null?((ParamsContext)_localctx).TYPE.getText():null));
			            // code java gérant la déclaration de "la variable" de retour de la fonction
			        
			setState(308);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__29) {
				{
				{
				setState(302);
				match(T__29);
				setState(303);
				((ParamsContext)_localctx).TYPE = match(TYPE);
				setState(304);
				((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

				                tablesSymboles.putVar((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null), (((ParamsContext)_localctx).TYPE!=null?((ParamsContext)_localctx).TYPE.getText():null));
				                // code java gérant une variable locale (argi)
				            
				}
				}
				setState(310);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public String code;
		public int size;
		public ExpressionContext expression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitArgs(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_args);
		 ((ArgsContext)_localctx).code =  new String(); ((ArgsContext)_localctx).size =  0; 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__5) | (1L << ENTIER) | (1L << FLOAT) | (1L << IDENTIFIANT))) != 0)) {
				{
				setState(311);
				((ArgsContext)_localctx).expression = expression(0);

				        ((ArgsContext)_localctx).code =  ((ArgsContext)_localctx).expression.code;
				        ((ArgsContext)_localctx).size =  AdresseType.getSize(((ArgsContext)_localctx).expression.type);
				        // code java pour première expression pour arg
				    
				setState(319);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__29) {
					{
					{
					setState(313);
					match(T__29);
					setState(314);
					((ArgsContext)_localctx).expression = expression(0);

					        _localctx.code += ((ArgsContext)_localctx).expression.code;
					        _localctx.size += AdresseType.getSize(((ArgsContext)_localctx).expression.type);
					        // code java pour expression suivante pour arg
					    
					}
					}
					setState(321);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 9:
			return condition_sempred((ConditionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		}
		return true;
	}
	private boolean condition_sempred(ConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3)\u0147\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\7\3+\n\3\f\3\16\3.\13\3\3\3\3\3\7\3\62\n\3\f"+
		"\3\16\3\65\13\3\3\3\3\3\3\3\7\3:\n\3\f\3\16\3=\13\3\3\3\7\3@\n\3\f\3\16"+
		"\3C\13\3\3\3\3\3\3\3\3\3\7\3I\n\3\f\3\16\3L\13\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4s\n\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\5\5\u008d\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\7\5\u0099\n\5\f\5\16\5\u009c\13\5\3\6\6\6\u009f\n\6\r\6\16\6\u00a0"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\5\7\u00b6\n\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\5\t\u00c6\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00dd\n\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00e9\n\13\f\13\16"+
		"\13\u00ec\13\13\3\f\3\f\3\f\3\f\6\f\u00f2\n\f\r\f\16\f\u00f3\3\f\3\f\7"+
		"\f\u00f8\n\f\f\f\16\f\u00fb\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u010d\n\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\3\20\5\20\u0127\n\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\7\21\u0135\n\21\f\21\16\21\u0138\13\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\7\22\u0140\n\22\f\22\16\22\u0143\13\22\5\22"+
		"\u0145\n\22\3\22\2\4\b\24\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"\2\6\3\2\5\6\3\2\7\b\4\2\t\t!!\3\2\21\26\2\u015b\2$\3\2\2\2\4,\3\2\2\2"+
		"\6r\3\2\2\2\b\u008c\3\2\2\2\n\u009e\3\2\2\2\f\u00b5\3\2\2\2\16\u00b7\3"+
		"\2\2\2\20\u00c5\3\2\2\2\22\u00c7\3\2\2\2\24\u00dc\3\2\2\2\26\u00ed\3\2"+
		"\2\2\30\u010c\3\2\2\2\32\u010e\3\2\2\2\34\u0119\3\2\2\2\36\u0121\3\2\2"+
		"\2 \u012d\3\2\2\2\"\u0144\3\2\2\2$%\5\4\3\2%&\7\2\2\3&\3\3\2\2\2\'(\5"+
		"\f\7\2()\b\3\1\2)+\3\2\2\2*\'\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-/"+
		"\3\2\2\2.,\3\2\2\2/\63\b\3\1\2\60\62\7!\2\2\61\60\3\2\2\2\62\65\3\2\2"+
		"\2\63\61\3\2\2\2\63\64\3\2\2\2\64;\3\2\2\2\65\63\3\2\2\2\66\67\5\36\20"+
		"\2\678\b\3\1\28:\3\2\2\29\66\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<A\3"+
		"\2\2\2=;\3\2\2\2>@\7!\2\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2BD\3"+
		"\2\2\2CA\3\2\2\2DJ\b\3\1\2EF\5\6\4\2FG\b\3\1\2GI\3\2\2\2HE\3\2\2\2IL\3"+
		"\2\2\2JH\3\2\2\2JK\3\2\2\2KM\3\2\2\2LJ\3\2\2\2MN\b\3\1\2N\5\3\2\2\2OP"+
		"\5\26\f\2PQ\b\4\1\2Qs\3\2\2\2RS\5\b\5\2ST\5\n\6\2TU\b\4\1\2Us\3\2\2\2"+
		"VW\5\16\b\2WX\5\n\6\2XY\b\4\1\2Ys\3\2\2\2Z[\5\20\t\2[\\\5\n\6\2\\]\b\4"+
		"\1\2]s\3\2\2\2^_\5\22\n\2_`\b\4\1\2`s\3\2\2\2ab\5\30\r\2bc\b\4\1\2cs\3"+
		"\2\2\2de\5\32\16\2ef\b\4\1\2fs\3\2\2\2gh\5\34\17\2hi\b\4\1\2is\3\2\2\2"+
		"jk\7&\2\2kl\5\b\5\2lm\5\n\6\2mn\b\4\1\2ns\3\2\2\2op\5\n\6\2pq\b\4\1\2"+
		"qs\3\2\2\2rO\3\2\2\2rR\3\2\2\2rV\3\2\2\2rZ\3\2\2\2r^\3\2\2\2ra\3\2\2\2"+
		"rd\3\2\2\2rg\3\2\2\2rj\3\2\2\2ro\3\2\2\2s\7\3\2\2\2tu\b\5\1\2uv\7\3\2"+
		"\2vw\5\b\5\2wx\7\4\2\2xy\b\5\1\2y\u008d\3\2\2\2z{\7#\2\2{\u008d\b\5\1"+
		"\2|}\7\b\2\2}~\7#\2\2~\u008d\b\5\1\2\177\u0080\7$\2\2\u0080\u008d\b\5"+
		"\1\2\u0081\u0082\7\b\2\2\u0082\u0083\7$\2\2\u0083\u008d\b\5\1\2\u0084"+
		"\u0085\7\'\2\2\u0085\u0086\7\3\2\2\u0086\u0087\5\"\22\2\u0087\u0088\7"+
		"\4\2\2\u0088\u0089\b\5\1\2\u0089\u008d\3\2\2\2\u008a\u008b\7\'\2\2\u008b"+
		"\u008d\b\5\1\2\u008ct\3\2\2\2\u008cz\3\2\2\2\u008c|\3\2\2\2\u008c\177"+
		"\3\2\2\2\u008c\u0081\3\2\2\2\u008c\u0084\3\2\2\2\u008c\u008a\3\2\2\2\u008d"+
		"\u009a\3\2\2\2\u008e\u008f\f\n\2\2\u008f\u0090\t\2\2\2\u0090\u0091\5\b"+
		"\5\13\u0091\u0092\b\5\1\2\u0092\u0099\3\2\2\2\u0093\u0094\f\t\2\2\u0094"+
		"\u0095\t\3\2\2\u0095\u0096\5\b\5\n\u0096\u0097\b\5\1\2\u0097\u0099\3\2"+
		"\2\2\u0098\u008e\3\2\2\2\u0098\u0093\3\2\2\2\u0099\u009c\3\2\2\2\u009a"+
		"\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\t\3\2\2\2\u009c\u009a\3\2\2\2"+
		"\u009d\u009f\t\4\2\2\u009e\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u009e"+
		"\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\13\3\2\2\2\u00a2\u00a3\7%\2\2\u00a3"+
		"\u00a4\7\'\2\2\u00a4\u00a5\7\n\2\2\u00a5\u00a6\7#\2\2\u00a6\u00a7\5\n"+
		"\6\2\u00a7\u00a8\b\7\1\2\u00a8\u00b6\3\2\2\2\u00a9\u00aa\7%\2\2\u00aa"+
		"\u00ab\7\'\2\2\u00ab\u00ac\5\n\6\2\u00ac\u00ad\b\7\1\2\u00ad\u00b6\3\2"+
		"\2\2\u00ae\u00af\7%\2\2\u00af\u00b0\7\'\2\2\u00b0\u00b1\7\n\2\2\u00b1"+
		"\u00b2\7$\2\2\u00b2\u00b3\5\n\6\2\u00b3\u00b4\b\7\1\2\u00b4\u00b6\3\2"+
		"\2\2\u00b5\u00a2\3\2\2\2\u00b5\u00a9\3\2\2\2\u00b5\u00ae\3\2\2\2\u00b6"+
		"\r\3\2\2\2\u00b7\u00b8\7\'\2\2\u00b8\u00b9\7\n\2\2\u00b9\u00ba\5\b\5\2"+
		"\u00ba\u00bb\b\b\1\2\u00bb\17\3\2\2\2\u00bc\u00bd\7\13\2\2\u00bd\u00be"+
		"\7\'\2\2\u00be\u00bf\7\4\2\2\u00bf\u00c6\b\t\1\2\u00c0\u00c1\7\f\2\2\u00c1"+
		"\u00c2\5\b\5\2\u00c2\u00c3\7\4\2\2\u00c3\u00c4\b\t\1\2\u00c4\u00c6\3\2"+
		"\2\2\u00c5\u00bc\3\2\2\2\u00c5\u00c0\3\2\2\2\u00c6\21\3\2\2\2\u00c7\u00c8"+
		"\7\r\2\2\u00c8\u00c9\7\3\2\2\u00c9\u00ca\5\24\13\2\u00ca\u00cb\7\4\2\2"+
		"\u00cb\u00cc\5\6\4\2\u00cc\u00cd\b\n\1\2\u00cd\23\3\2\2\2\u00ce\u00cf"+
		"\b\13\1\2\u00cf\u00d0\7\20\2\2\u00d0\u00d1\5\24\13\6\u00d1\u00d2\b\13"+
		"\1\2\u00d2\u00dd\3\2\2\2\u00d3\u00d4\5\b\5\2\u00d4\u00d5\t\5\2\2\u00d5"+
		"\u00d6\5\b\5\2\u00d6\u00d7\b\13\1\2\u00d7\u00dd\3\2\2\2\u00d8\u00d9\7"+
		"\27\2\2\u00d9\u00dd\b\13\1\2\u00da\u00db\7\30\2\2\u00db\u00dd\b\13\1\2"+
		"\u00dc\u00ce\3\2\2\2\u00dc\u00d3\3\2\2\2\u00dc\u00d8\3\2\2\2\u00dc\u00da"+
		"\3\2\2\2\u00dd\u00ea\3\2\2\2\u00de\u00df\f\b\2\2\u00df\u00e0\7\16\2\2"+
		"\u00e0\u00e1\5\24\13\t\u00e1\u00e2\b\13\1\2\u00e2\u00e9\3\2\2\2\u00e3"+
		"\u00e4\f\7\2\2\u00e4\u00e5\7\17\2\2\u00e5\u00e6\5\24\13\b\u00e6\u00e7"+
		"\b\13\1\2\u00e7\u00e9\3\2\2\2\u00e8\u00de\3\2\2\2\u00e8\u00e3\3\2\2\2"+
		"\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\25"+
		"\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00f1\7\31\2\2\u00ee\u00ef\5\6\4\2"+
		"\u00ef\u00f0\b\f\1\2\u00f0\u00f2\3\2\2\2\u00f1\u00ee\3\2\2\2\u00f2\u00f3"+
		"\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5"+
		"\u00f9\7\32\2\2\u00f6\u00f8\7!\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00fb\3\2"+
		"\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\27\3\2\2\2\u00fb\u00f9"+
		"\3\2\2\2\u00fc\u00fd\7\33\2\2\u00fd\u00fe\7\3\2\2\u00fe\u00ff\5\24\13"+
		"\2\u00ff\u0100\7\4\2\2\u0100\u0101\5\6\4\2\u0101\u0102\b\r\1\2\u0102\u010d"+
		"\3\2\2\2\u0103\u0104\7\33\2\2\u0104\u0105\7\3\2\2\u0105\u0106\5\24\13"+
		"\2\u0106\u0107\7\4\2\2\u0107\u0108\5\6\4\2\u0108\u0109\7\34\2\2\u0109"+
		"\u010a\5\6\4\2\u010a\u010b\b\r\1\2\u010b\u010d\3\2\2\2\u010c\u00fc\3\2"+
		"\2\2\u010c\u0103\3\2\2\2\u010d\31\3\2\2\2\u010e\u010f\7\35\2\2\u010f\u0110"+
		"\7\3\2\2\u0110\u0111\5\16\b\2\u0111\u0112\7\t\2\2\u0112\u0113\5\24\13"+
		"\2\u0113\u0114\7\t\2\2\u0114\u0115\5\16\b\2\u0115\u0116\7\4\2\2\u0116"+
		"\u0117\5\6\4\2\u0117\u0118\b\16\1\2\u0118\33\3\2\2\2\u0119\u011a\7\36"+
		"\2\2\u011a\u011b\5\6\4\2\u011b\u011c\7\37\2\2\u011c\u011d\7\3\2\2\u011d"+
		"\u011e\5\24\13\2\u011e\u011f\7\4\2\2\u011f\u0120\b\17\1\2\u0120\35\3\2"+
		"\2\2\u0121\u0122\7%\2\2\u0122\u0123\b\20\1\2\u0123\u0124\7\'\2\2\u0124"+
		"\u0126\7\3\2\2\u0125\u0127\5 \21\2\u0126\u0125\3\2\2\2\u0126\u0127\3\2"+
		"\2\2\u0127\u0128\3\2\2\2\u0128\u0129\7\4\2\2\u0129\u012a\b\20\1\2\u012a"+
		"\u012b\5\26\f\2\u012b\u012c\b\20\1\2\u012c\37\3\2\2\2\u012d\u012e\7%\2"+
		"\2\u012e\u012f\7\'\2\2\u012f\u0136\b\21\1\2\u0130\u0131\7 \2\2\u0131\u0132"+
		"\7%\2\2\u0132\u0133\7\'\2\2\u0133\u0135\b\21\1\2\u0134\u0130\3\2\2\2\u0135"+
		"\u0138\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137!\3\2\2\2"+
		"\u0138\u0136\3\2\2\2\u0139\u013a\5\b\5\2\u013a\u0141\b\22\1\2\u013b\u013c"+
		"\7 \2\2\u013c\u013d\5\b\5\2\u013d\u013e\b\22\1\2\u013e\u0140\3\2\2\2\u013f"+
		"\u013b\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0142\3\2"+
		"\2\2\u0142\u0145\3\2\2\2\u0143\u0141\3\2\2\2\u0144\u0139\3\2\2\2\u0144"+
		"\u0145\3\2\2\2\u0145#\3\2\2\2\30,\63;AJr\u008c\u0098\u009a\u00a0\u00b5"+
		"\u00c5\u00dc\u00e8\u00ea\u00f3\u00f9\u010c\u0126\u0136\u0141\u0144";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}