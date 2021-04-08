grammar Calculette;

@parser::members {

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
}


start
    : calcul EOF;

calcul returns [ String code ]
@init{ $code = new String(); }   // On initialise code, pour ensuite l'utiliser comme accumulateur
@after{ System.out.println($code); }
    :   (decl { $code += $decl.code; })*
        
        NEWLINE*

        (instruction { $code += $instruction.code; })*

        { $code += "  HALT\n"; }
    ;

instruction returns [ String code ] 
   	: bloc
	{
		$code = $bloc.code;
	}
    | expression finInstruction 
	{ 
	    $code = $expression.code;
	}
    | assignation finInstruction
	{
		$code = $assignation.code;
	}
   	| input finInstruction 
	{
		$code = $input.code;
	}
   	| boucle
	{
		$code = $boucle.code;
	}
   	| codeif
   	{
   		$code = $codeif.code;
   	}
   	| codefor
   	{
   		$code = $codefor.code;
   	}
   	| coderepeat
   	{
   		$code = $coderepeat.code;
   	}
    | finInstruction
    {
        $code="";
    }
    ;

expression returns [ String code ]
    : 
    '(' a=expression ')' { $code = $a.code; }
    | a=expression op=('*'|'/') b=expression { $code = $a.code + $b.code + mvapEval($op.text) + "\n"; }
    | a=expression op=('+'|'-') b=expression { $code = $a.code + $b.code + mvapEval($op.text) + "\n"; }
    | ENTIER { $code = "PUSHI " + $ENTIER.text + "\n"; }
    | '-' ENTIER { $code = "PUSHI -" + $ENTIER.text + "\n"; }
    | IDENTIFIANT 
    	{
    		AdresseType at = tablesSymboles.getAdresseType($IDENTIFIANT.text);
    		$code = "PUSHG " + at.adresse + "\n";
    	}
    ;

finInstruction : ( NEWLINE | ';' )+ ;


decl returns [ String code ]
    :
    	TYPE IDENTIFIANT '=' ENTIER finInstruction
        {
        	tablesSymboles.putVar($IDENTIFIANT.text, $TYPE.text);
            $code = "PUSHI 0\n";
            $code += "PUSHI " + $ENTIER.text + "\n";
            AdresseType at = tablesSymboles.getAdresseType($IDENTIFIANT.text);
        	$code += "STOREG " + at.adresse + "\n";
        }
	|
        TYPE IDENTIFIANT finInstruction
        {
        	tablesSymboles.putVar($IDENTIFIANT.text, $TYPE.text);
            $code = "PUSHI 0\n";
        }
    ;

assignation returns [ String code ] 
    : 
    	IDENTIFIANT '=' a=expression
        {
        	AdresseType at = tablesSymboles.getAdresseType($IDENTIFIANT.text);
        	$code = $a.code + "STOREG " + at.adresse + "\n";
        }
    ;

input returns [ String code ]
	:
		'read(' IDENTIFIANT ')'
		{
			$code = "READ\n";
			AdresseType at = tablesSymboles.getAdresseType($IDENTIFIANT.text);
        	$code += "STOREG " + at.adresse + "\n";
		}
		|
		'write(' a=expression ')'
		{
			$code = $a.code;
	    	$code += "WRITE\n";
	    	$code += "POP\n";
		}
	;

boucle returns [ String code ]
	:
	'while' '(' a=condition ')' b=instruction
	{
		$code = evalboucle($a.code, $b.code);
	}
	;

condition returns [String code]
	: c=condition '&&' d=condition
	{
		$code = $c.code + $d.code + evallog("&&");
	}
	| c=condition '||' d=condition
	{
		$code = $c.code + $d.code +evallog("||");
	}
	| '!' c=condition
	{
		$code = $c.code + evallog("!");
	}
	| a=expression op=('=='|'!='|'>'|'>='|'<'|'<=') b=expression
	{
		$code = evalCond($a.code, $op.text, $b.code);
	}
    | 'true'  { $code = "PUSHI 1\n"; }
    | 'false' { $code = "PUSHI 0\n"; }
    ;

bloc returns [String code]
	@init{ $code = "";}
	: '{' (instruction { $code += $instruction.code;})+ '}' NEWLINE*
	;


codeif returns [ String code ]
	: 'if' '(' a=condition ')' b=instruction
	{

		$code = evalif($a.code, $b.code);
	}
	| 'if' '(' a=condition ')' b=instruction 'else' c=instruction
	{
		$code = evalifelse($a.code, $b.code, $c.code);
	}
	;

codefor returns [ String code]
	: 'for' '(' a=assignation ';' c=condition ';' b=assignation ')' i=instruction
	{
		$code = evalfor($a.code, $c.code, $b.code, $i.code);
	}
	;


coderepeat returns [ String code ]
	: 'repeat' i=instruction 'until' '(' c=condition ')'
	{
		$code = evalRepeat($i.code, $c.code);
	}
	;

// lexer
NEWLINE : '\r'? '\n';

WS :   (' '|'\t')+ -> skip ;

ENTIER : ('0'..'9')+  ;

TYPE : 'int' | 'float' ;

IDENTIFIANT : ('a'..'z')+  ;

LONG_COMMENT : '/*' .*? '*/' -> skip ;

SHORT_COMMENT : '//' .*? '\n' -> skip ;