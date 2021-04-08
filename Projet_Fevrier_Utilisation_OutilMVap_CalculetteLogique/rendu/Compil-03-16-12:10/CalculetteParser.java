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
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, NEWLINE=30, WS=31, ENTIER=32, 
		TYPE=33, IDENTIFIANT=34, LONG_COMMENT=35, SHORT_COMMENT=36;
	public static final int
		RULE_start = 0, RULE_calcul = 1, RULE_instruction = 2, RULE_expression = 3, 
		RULE_finInstruction = 4, RULE_decl = 5, RULE_assignation = 6, RULE_input = 7, 
		RULE_boucle = 8, RULE_condition = 9, RULE_bloc = 10, RULE_codeif = 11, 
		RULE_codefor = 12, RULE_coderepeat = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "calcul", "instruction", "expression", "finInstruction", "decl", 
			"assignation", "input", "boucle", "condition", "bloc", "codeif", "codefor", 
			"coderepeat"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'*'", "'/'", "'+'", "'-'", "';'", "'='", "'read('", 
			"'write('", "'while'", "'&&'", "'||'", "'!'", "'=='", "'!='", "'>'", 
			"'>='", "'<'", "'<='", "'true'", "'false'", "'{'", "'}'", "'if'", "'else'", 
			"'for'", "'repeat'", "'until'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "NEWLINE", "WS", "ENTIER", "TYPE", 
			"IDENTIFIANT", "LONG_COMMENT", "SHORT_COMMENT"
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
			setState(28);
			calcul();
			setState(29);
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
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE) {
				{
				{
				setState(31);
				((CalculContext)_localctx).decl = decl();
				 _localctx.code += ((CalculContext)_localctx).decl.code; 
				}
				}
				setState(38);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(42);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(39);
					match(NEWLINE);
					}
					} 
				}
				setState(44);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__22) | (1L << T__24) | (1L << T__26) | (1L << T__27) | (1L << NEWLINE) | (1L << ENTIER) | (1L << IDENTIFIANT))) != 0)) {
				{
				{
				setState(45);
				((CalculContext)_localctx).instruction = instruction();
				 _localctx.code += ((CalculContext)_localctx).instruction.code; 
				}
				}
				setState(52);
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
			setState(85);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				((InstructionContext)_localctx).bloc = bloc();

						((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).bloc.code;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				((InstructionContext)_localctx).expression = expression(0);
				setState(59);
				finInstruction();
				 
					    ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).expression.code;
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				((InstructionContext)_localctx).assignation = assignation();
				setState(63);
				finInstruction();

						((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).assignation.code;
					
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(66);
				((InstructionContext)_localctx).input = input();
				setState(67);
				finInstruction();

						((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).input.code;
					
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(70);
				((InstructionContext)_localctx).boucle = boucle();

						((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).boucle.code;
					
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(73);
				((InstructionContext)_localctx).codeif = codeif();

				   		((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).codeif.code;
				   	
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(76);
				((InstructionContext)_localctx).codefor = codefor();

				   		((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).codefor.code;
				   	
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(79);
				((InstructionContext)_localctx).coderepeat = coderepeat();

				   		((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).coderepeat.code;
				   	
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(82);
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
		public ExpressionContext a;
		public Token ENTIER;
		public Token IDENTIFIANT;
		public Token op;
		public ExpressionContext b;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ENTIER() { return getToken(CalculetteParser.ENTIER, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
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
			setState(100);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				setState(88);
				match(T__0);
				setState(89);
				((ExpressionContext)_localctx).a = expression(0);
				setState(90);
				match(T__1);
				 ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code; 
				}
				break;
			case ENTIER:
				{
				setState(93);
				((ExpressionContext)_localctx).ENTIER = match(ENTIER);
				 ((ExpressionContext)_localctx).code =  "PUSHI " + (((ExpressionContext)_localctx).ENTIER!=null?((ExpressionContext)_localctx).ENTIER.getText():null) + "\n"; 
				}
				break;
			case T__5:
				{
				setState(95);
				match(T__5);
				setState(96);
				((ExpressionContext)_localctx).ENTIER = match(ENTIER);
				 ((ExpressionContext)_localctx).code =  "PUSHI -" + (((ExpressionContext)_localctx).ENTIER!=null?((ExpressionContext)_localctx).ENTIER.getText():null) + "\n"; 
				}
				break;
			case IDENTIFIANT:
				{
				setState(98);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

				    		AdresseType at = tablesSymboles.getAdresseType((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null));
				    		((ExpressionContext)_localctx).code =  "PUSHG " + at.adresse + "\n";
				    	
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(114);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(112);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(102);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(103);
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
						setState(104);
						((ExpressionContext)_localctx).b = expression(6);
						 ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + ((ExpressionContext)_localctx).b.code + mvapEval((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)) + "\n"; 
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(107);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(108);
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
						setState(109);
						((ExpressionContext)_localctx).b = expression(5);
						 ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + ((ExpressionContext)_localctx).b.code + mvapEval((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)) + "\n"; 
						}
						break;
					}
					} 
				}
				setState(116);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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
			setState(118); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(117);
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
				setState(120); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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
		public TerminalNode TYPE() { return getToken(CalculetteParser.TYPE, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public TerminalNode ENTIER() { return getToken(CalculetteParser.ENTIER, 0); }
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
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
			setState(134);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				((DeclContext)_localctx).TYPE = match(TYPE);
				setState(123);
				((DeclContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(124);
				match(T__7);
				setState(125);
				((DeclContext)_localctx).ENTIER = match(ENTIER);
				setState(126);
				finInstruction();

				        	tablesSymboles.putVar((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null), (((DeclContext)_localctx).TYPE!=null?((DeclContext)_localctx).TYPE.getText():null));
				            ((DeclContext)_localctx).code =  "PUSHI 0\n";
				            _localctx.code += "PUSHI " + (((DeclContext)_localctx).ENTIER!=null?((DeclContext)_localctx).ENTIER.getText():null) + "\n";
				            AdresseType at = tablesSymboles.getAdresseType((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null));
				        	_localctx.code += "STOREG " + at.adresse + "\n";
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				((DeclContext)_localctx).TYPE = match(TYPE);
				setState(130);
				((DeclContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(131);
				finInstruction();

				        	tablesSymboles.putVar((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null), (((DeclContext)_localctx).TYPE!=null?((DeclContext)_localctx).TYPE.getText():null));
				            ((DeclContext)_localctx).code =  "PUSHI 0\n";
				        
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
			setState(136);
			((AssignationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
			setState(137);
			match(T__7);
			setState(138);
			((AssignationContext)_localctx).a = expression(0);

			        	AdresseType at = tablesSymboles.getAdresseType((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null));
			        	((AssignationContext)_localctx).code =  ((AssignationContext)_localctx).a.code + "STOREG " + at.adresse + "\n";
			        
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
			setState(150);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				match(T__8);
				setState(142);
				((InputContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(143);
				match(T__1);

							((InputContext)_localctx).code =  "READ\n";
							AdresseType at = tablesSymboles.getAdresseType((((InputContext)_localctx).IDENTIFIANT!=null?((InputContext)_localctx).IDENTIFIANT.getText():null));
				        	_localctx.code += "STOREG " + at.adresse + "\n";
						
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				match(T__9);
				setState(146);
				((InputContext)_localctx).a = expression(0);
				setState(147);
				match(T__1);

							((InputContext)_localctx).code =  ((InputContext)_localctx).a.code;
					    	_localctx.code += "WRITE\n";
					    	_localctx.code += "POP\n";
						
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
			setState(152);
			match(T__10);
			setState(153);
			match(T__0);
			setState(154);
			((BoucleContext)_localctx).a = condition(0);
			setState(155);
			match(T__1);
			setState(156);
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
			setState(173);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
				{
				setState(160);
				match(T__13);
				setState(161);
				((ConditionContext)_localctx).c = condition(4);

						((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).c.code + evallog("!");
					
				}
				break;
			case T__0:
			case T__5:
			case ENTIER:
			case IDENTIFIANT:
				{
				setState(164);
				((ConditionContext)_localctx).a = expression(0);
				setState(165);
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
				setState(166);
				((ConditionContext)_localctx).b = expression(0);

						((ConditionContext)_localctx).code =  evalCond(((ConditionContext)_localctx).a.code, (((ConditionContext)_localctx).op!=null?((ConditionContext)_localctx).op.getText():null), ((ConditionContext)_localctx).b.code);
					
				}
				break;
			case T__20:
				{
				setState(169);
				match(T__20);
				 ((ConditionContext)_localctx).code =  "PUSHI 1\n"; 
				}
				break;
			case T__21:
				{
				setState(171);
				match(T__21);
				 ((ConditionContext)_localctx).code =  "PUSHI 0\n"; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(187);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(185);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new ConditionContext(_parentctx, _parentState);
						_localctx.c = _prevctx;
						_localctx.c = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(175);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(176);
						match(T__11);
						setState(177);
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
						setState(180);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(181);
						match(T__12);
						setState(182);
						((ConditionContext)_localctx).d = condition(6);

						          		((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).c.code + ((ConditionContext)_localctx).d.code +evallog("||");
						          	
						}
						break;
					}
					} 
				}
				setState(189);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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
			setState(190);
			match(T__22);
			setState(194); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(191);
				((BlocContext)_localctx).instruction = instruction();
				 _localctx.code += ((BlocContext)_localctx).instruction.code;
				}
				}
				setState(196); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__22) | (1L << T__24) | (1L << T__26) | (1L << T__27) | (1L << NEWLINE) | (1L << ENTIER) | (1L << IDENTIFIANT))) != 0) );
			setState(198);
			match(T__23);
			setState(202);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(199);
					match(NEWLINE);
					}
					} 
				}
				setState(204);
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
			setState(221);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(205);
				match(T__24);
				setState(206);
				match(T__0);
				setState(207);
				((CodeifContext)_localctx).a = condition(0);
				setState(208);
				match(T__1);
				setState(209);
				((CodeifContext)_localctx).b = instruction();


						((CodeifContext)_localctx).code =  evalif(((CodeifContext)_localctx).a.code, ((CodeifContext)_localctx).b.code);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(212);
				match(T__24);
				setState(213);
				match(T__0);
				setState(214);
				((CodeifContext)_localctx).a = condition(0);
				setState(215);
				match(T__1);
				setState(216);
				((CodeifContext)_localctx).b = instruction();
				setState(217);
				match(T__25);
				setState(218);
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
			setState(223);
			match(T__26);
			setState(224);
			match(T__0);
			setState(225);
			((CodeforContext)_localctx).a = assignation();
			setState(226);
			match(T__6);
			setState(227);
			((CodeforContext)_localctx).c = condition(0);
			setState(228);
			match(T__6);
			setState(229);
			((CodeforContext)_localctx).b = assignation();
			setState(230);
			match(T__1);
			setState(231);
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
			setState(234);
			match(T__27);
			setState(235);
			((CoderepeatContext)_localctx).i = instruction();
			setState(236);
			match(T__28);
			setState(237);
			match(T__0);
			setState(238);
			((CoderepeatContext)_localctx).c = condition(0);
			setState(239);
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
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3&\u00f5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\3\3\3\3\3\7\3%\n"+
		"\3\f\3\16\3(\13\3\3\3\7\3+\n\3\f\3\16\3.\13\3\3\3\3\3\3\3\7\3\63\n\3\f"+
		"\3\16\3\66\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\5\4X\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5"+
		"g\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5s\n\5\f\5\16\5v\13\5"+
		"\3\6\6\6y\n\6\r\6\16\6z\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\5\7\u0089\n\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\5\t\u0099\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00b0\n\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00bc\n\13\f\13\16\13\u00bf"+
		"\13\13\3\f\3\f\3\f\3\f\6\f\u00c5\n\f\r\f\16\f\u00c6\3\f\3\f\7\f\u00cb"+
		"\n\f\f\f\16\f\u00ce\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\5\r\u00e0\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\2\4\b"+
		"\24\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\6\3\2\5\6\3\2\7\b\4\2\t\t"+
		"  \3\2\21\26\2\u0101\2\36\3\2\2\2\4&\3\2\2\2\6W\3\2\2\2\bf\3\2\2\2\nx"+
		"\3\2\2\2\f\u0088\3\2\2\2\16\u008a\3\2\2\2\20\u0098\3\2\2\2\22\u009a\3"+
		"\2\2\2\24\u00af\3\2\2\2\26\u00c0\3\2\2\2\30\u00df\3\2\2\2\32\u00e1\3\2"+
		"\2\2\34\u00ec\3\2\2\2\36\37\5\4\3\2\37 \7\2\2\3 \3\3\2\2\2!\"\5\f\7\2"+
		"\"#\b\3\1\2#%\3\2\2\2$!\3\2\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\',\3\2"+
		"\2\2(&\3\2\2\2)+\7 \2\2*)\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\64\3"+
		"\2\2\2.,\3\2\2\2/\60\5\6\4\2\60\61\b\3\1\2\61\63\3\2\2\2\62/\3\2\2\2\63"+
		"\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\67\3\2\2\2\66\64\3\2\2\2\67"+
		"8\b\3\1\28\5\3\2\2\29:\5\26\f\2:;\b\4\1\2;X\3\2\2\2<=\5\b\5\2=>\5\n\6"+
		"\2>?\b\4\1\2?X\3\2\2\2@A\5\16\b\2AB\5\n\6\2BC\b\4\1\2CX\3\2\2\2DE\5\20"+
		"\t\2EF\5\n\6\2FG\b\4\1\2GX\3\2\2\2HI\5\22\n\2IJ\b\4\1\2JX\3\2\2\2KL\5"+
		"\30\r\2LM\b\4\1\2MX\3\2\2\2NO\5\32\16\2OP\b\4\1\2PX\3\2\2\2QR\5\34\17"+
		"\2RS\b\4\1\2SX\3\2\2\2TU\5\n\6\2UV\b\4\1\2VX\3\2\2\2W9\3\2\2\2W<\3\2\2"+
		"\2W@\3\2\2\2WD\3\2\2\2WH\3\2\2\2WK\3\2\2\2WN\3\2\2\2WQ\3\2\2\2WT\3\2\2"+
		"\2X\7\3\2\2\2YZ\b\5\1\2Z[\7\3\2\2[\\\5\b\5\2\\]\7\4\2\2]^\b\5\1\2^g\3"+
		"\2\2\2_`\7\"\2\2`g\b\5\1\2ab\7\b\2\2bc\7\"\2\2cg\b\5\1\2de\7$\2\2eg\b"+
		"\5\1\2fY\3\2\2\2f_\3\2\2\2fa\3\2\2\2fd\3\2\2\2gt\3\2\2\2hi\f\7\2\2ij\t"+
		"\2\2\2jk\5\b\5\bkl\b\5\1\2ls\3\2\2\2mn\f\6\2\2no\t\3\2\2op\5\b\5\7pq\b"+
		"\5\1\2qs\3\2\2\2rh\3\2\2\2rm\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2u\t"+
		"\3\2\2\2vt\3\2\2\2wy\t\4\2\2xw\3\2\2\2yz\3\2\2\2zx\3\2\2\2z{\3\2\2\2{"+
		"\13\3\2\2\2|}\7#\2\2}~\7$\2\2~\177\7\n\2\2\177\u0080\7\"\2\2\u0080\u0081"+
		"\5\n\6\2\u0081\u0082\b\7\1\2\u0082\u0089\3\2\2\2\u0083\u0084\7#\2\2\u0084"+
		"\u0085\7$\2\2\u0085\u0086\5\n\6\2\u0086\u0087\b\7\1\2\u0087\u0089\3\2"+
		"\2\2\u0088|\3\2\2\2\u0088\u0083\3\2\2\2\u0089\r\3\2\2\2\u008a\u008b\7"+
		"$\2\2\u008b\u008c\7\n\2\2\u008c\u008d\5\b\5\2\u008d\u008e\b\b\1\2\u008e"+
		"\17\3\2\2\2\u008f\u0090\7\13\2\2\u0090\u0091\7$\2\2\u0091\u0092\7\4\2"+
		"\2\u0092\u0099\b\t\1\2\u0093\u0094\7\f\2\2\u0094\u0095\5\b\5\2\u0095\u0096"+
		"\7\4\2\2\u0096\u0097\b\t\1\2\u0097\u0099\3\2\2\2\u0098\u008f\3\2\2\2\u0098"+
		"\u0093\3\2\2\2\u0099\21\3\2\2\2\u009a\u009b\7\r\2\2\u009b\u009c\7\3\2"+
		"\2\u009c\u009d\5\24\13\2\u009d\u009e\7\4\2\2\u009e\u009f\5\6\4\2\u009f"+
		"\u00a0\b\n\1\2\u00a0\23\3\2\2\2\u00a1\u00a2\b\13\1\2\u00a2\u00a3\7\20"+
		"\2\2\u00a3\u00a4\5\24\13\6\u00a4\u00a5\b\13\1\2\u00a5\u00b0\3\2\2\2\u00a6"+
		"\u00a7\5\b\5\2\u00a7\u00a8\t\5\2\2\u00a8\u00a9\5\b\5\2\u00a9\u00aa\b\13"+
		"\1\2\u00aa\u00b0\3\2\2\2\u00ab\u00ac\7\27\2\2\u00ac\u00b0\b\13\1\2\u00ad"+
		"\u00ae\7\30\2\2\u00ae\u00b0\b\13\1\2\u00af\u00a1\3\2\2\2\u00af\u00a6\3"+
		"\2\2\2\u00af\u00ab\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00bd\3\2\2\2\u00b1"+
		"\u00b2\f\b\2\2\u00b2\u00b3\7\16\2\2\u00b3\u00b4\5\24\13\t\u00b4\u00b5"+
		"\b\13\1\2\u00b5\u00bc\3\2\2\2\u00b6\u00b7\f\7\2\2\u00b7\u00b8\7\17\2\2"+
		"\u00b8\u00b9\5\24\13\b\u00b9\u00ba\b\13\1\2\u00ba\u00bc\3\2\2\2\u00bb"+
		"\u00b1\3\2\2\2\u00bb\u00b6\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb\3\2"+
		"\2\2\u00bd\u00be\3\2\2\2\u00be\25\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c4"+
		"\7\31\2\2\u00c1\u00c2\5\6\4\2\u00c2\u00c3\b\f\1\2\u00c3\u00c5\3\2\2\2"+
		"\u00c4\u00c1\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7"+
		"\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00cc\7\32\2\2\u00c9\u00cb\7 \2\2\u00ca"+
		"\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2"+
		"\2\2\u00cd\27\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0\7\33\2\2\u00d0\u00d1"+
		"\7\3\2\2\u00d1\u00d2\5\24\13\2\u00d2\u00d3\7\4\2\2\u00d3\u00d4\5\6\4\2"+
		"\u00d4\u00d5\b\r\1\2\u00d5\u00e0\3\2\2\2\u00d6\u00d7\7\33\2\2\u00d7\u00d8"+
		"\7\3\2\2\u00d8\u00d9\5\24\13\2\u00d9\u00da\7\4\2\2\u00da\u00db\5\6\4\2"+
		"\u00db\u00dc\7\34\2\2\u00dc\u00dd\5\6\4\2\u00dd\u00de\b\r\1\2\u00de\u00e0"+
		"\3\2\2\2\u00df\u00cf\3\2\2\2\u00df\u00d6\3\2\2\2\u00e0\31\3\2\2\2\u00e1"+
		"\u00e2\7\35\2\2\u00e2\u00e3\7\3\2\2\u00e3\u00e4\5\16\b\2\u00e4\u00e5\7"+
		"\t\2\2\u00e5\u00e6\5\24\13\2\u00e6\u00e7\7\t\2\2\u00e7\u00e8\5\16\b\2"+
		"\u00e8\u00e9\7\4\2\2\u00e9\u00ea\5\6\4\2\u00ea\u00eb\b\16\1\2\u00eb\33"+
		"\3\2\2\2\u00ec\u00ed\7\36\2\2\u00ed\u00ee\5\6\4\2\u00ee\u00ef\7\37\2\2"+
		"\u00ef\u00f0\7\3\2\2\u00f0\u00f1\5\24\13\2\u00f1\u00f2\7\4\2\2\u00f2\u00f3"+
		"\b\17\1\2\u00f3\35\3\2\2\2\22&,\64Wfrtz\u0088\u0098\u00af\u00bb\u00bd"+
		"\u00c6\u00cc\u00df";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}