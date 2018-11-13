package com.anatawa12.libs.csv

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * Created by anatawa12 on 2018/11/12.
 */
class CsvTest1 {
	val test1Ary = arrayOf(
			arrayOf(
					"G}=:-V`HBhW&?gu9W\"\\\$;)dh+Vr",
					"Qb9rjktait?0vj)i ;Z(,8`",
					"JW#`(uv(LqgxIFU\"UDG uy",
					"Ar*6t8a|lZpYm=\$",
					"O:1+Fg^mq;pN'w6k.W^mFG+|\$A`{!d|*'iS,.",
					"j",
					"p[Iy[+4GDgBE/*'][.N7|dt\$Aa?}bX\\}rd^U,bL'\\<y&1*v",
					"`[w6ZM)0 tM{jCm<h|",
					"54Zn\"e(",
					"Qrl@G:dmHMgQq/i\$YjV@+'%",
					"3b+6f2(:r@[-[%#Tyu8^?mP\\z8",
					"6I<[A7.Ady"
			),
			arrayOf(
					"Tb_w>bM60gq.8m/{} KHpf+\"2c}!uC`u>*1WzVx-yG*BX",
					"4`lkD('vwn+'LGnp YzvF4%m[",
					"<jE;tIB+EKW&^^}l",
					"md^\"fob:rrl>rjjdHB@+W\$WwBfbFj.j",
					";wpQxZRM<T'|zyQcj|O0",
					"7u|:>>M)qcY.8tL/Z",
					"E>LdX[;i[L3[ l)O,^bNNOyx77Y",
					"!Zb,ph,vonGWPps!>0_>a.)*<+1V",
					"Vr{2TqRt{M+eW,Jz",
					"))\",xn=\$UC6`w?}9,`cSM|ussF=FgGb",
					"9NmS>LL:`1!\"^lu&_qC6 )BNrybOnQr",
					"ic\\G2FmD*M9Kb^"
			),
			arrayOf(
					"b%V.",
					"sqIIUGoPTd",
					"H-PI{Qe\$2HvKPvtb",
					"}{M/r.V&V\\S+RZ*[gAEIy 5cn8tL=`iHg\"{]!K1C{]xa",
					"6CY|HDb-Bz}Lxe-{{;zO-YU4'jd9&#Ucdi39;3kz^UxC[ilC",
					"YP_@#",
					"NG 3JkwkV:IKz.<\"P5W[)yZP\\UIs`ApO21mzNlO]7W\\Ssc",
					"g\\fj<| ",
					"9il5 Sn8&Q:",
					"fK}89^'iFb_YAI:Zqw\"75r>K?%\\K7+,>Ob(2':?} is&)hf){",
					"UEk^k>",
					"*"
			),
			arrayOf(
					"EmAH:{CSN9k?hO_oW{83Us]k",
					".<4dl=8f)zl--.",
					"u*5e19- `ri8a/e0z(07trw]MyzM/E<u",
					"64bT2jMz_m6uA8Mhh)",
					"#|vf2)",
					":'Cobd>v@K:*z\"6UHpN ;&TJF,PPg\$ ;>#vMZ+E:",
					"x\$3LT[zZoK7vRr<;05UG mc,KY{;23jf:l_9_M}",
					"#,F7b`WzME]R%(=?`2C,ie|>9.GM8w|{\$+1R:E]!X.wF6`JjN",
					"quHZ[]8T2",
					"F{\${I7n6-&XM]tT}+>Lpfe01YjT9LW_.",
					"FFy&Z*').Z:Nq(",
					"?>F^=Ydz !G9r\\f=H0pzJH9|9r<#&0zN;)76p;kE*8w"
			),
			arrayOf(
					"5k=Q]-7%,fl;rl'uA:a|Ho;'>-K1Y\"I",
					"?",
					"FFS_Z90mO",
					"",
					"')Q>",
					"vemf{a",
					"DMFR+D:{b)40\"\$eQ{",
					"3pa!L4`g",
					"|L>",
					"k*?lhU62Wf;- Bc86|-pl_]SCm>pV?bYG)EsP[@&5",
					"CO'1Py//\"G:\$r_Y-Ud<O:.",
					".t"
			),
			arrayOf(
					"-8iD.FiZ|cDOb&Z<M0G932Mgq]U|YlDNOjioR_yfw,UGai}",
					"P /,=wfb\\0\\zbWH\\1bc/|^_.-ux@HKD>I>GU{W0/iZfD>YH",
					" S\$M]f+`-zn+*+%Q]@Ei7b>1EgcGc(@}>QlfO2.S:|CN%sz^",
					"@/>i&bS",
					"Wq679Dch=01Lk20cJNL9m^f",
					"Hh",
					"ibH#*\\)",
					"(q*1\"y{Mm!=g",
					"P0;8`mwU.FryrQla:|&HRAY!coURjVG\"|;\"I0cNY",
					"I6n/k!i&!}ynn*w;g\"]L?K@7&{,f@;zVC",
					"bGvVr\\).`|o@h",
					"\"m>OXW\"h|A'r[_1)YFYH]O=q(@K?9\"9{KWqU*e3"
			),
			arrayOf(
					"c;j*\$ Ct*6R",
					":4SNW02/o@jZBprtLG79\$2!",
					"qz=aW`R:gE&oPZf4R8bMb-@Ie{i{l",
					"l\\kINbLIc",
					"zZ\\Wz?u`DT{`.NQF_)E;1]yO@4J) `|z4i",
					"T>{F:?",
					"",
					"U&PY?ya+O0Qg&@4^)LTs\"< 6W&?_U-`",
					"^u/>9%,f:",
					"Df\"[IBQv8V8E!WGGSsic^G",
					"4",
					""
			),
			arrayOf(
					"|DyTO07b/t)0#=5>>-90XFvqF1",
					"<j",
					"WHrrhPpz'2c*ql5V:A3uIX::;",
					"!o`@FEy`^7`z:#Ub-'*",
					"?wJ=M^kP\\Ie|`<*x-]D23T\"G",
					"@8i }`C)5DN(|0XGB2gcPxST[cl\$]H\"x'Yjz@.M='_bv5.S_",
					"x^62HWlP{<JNWgZy4;)vY3JkuNGa",
					"4su*%Q`=3",
					"7srW/+O5Y|lzNmFDVyUWbbI-h!%8/7@j\\(?SKdK6;QdF0wj",
					"!917?A?n(se{DyEJZYG.<N29./w^HK810'MQ[! 9mX",
					"xJAmo^{thFK1oU3gE_@3?(js=:\\\\D\"'pm%^q-fZvB4Et7*%",
					"`=d<WN,V>+ Jav[&`=\"\"w}=Q.FD([D=\"]^.N7S[z"
			)
	)
	val test1Csv = "\"G}=:-V`HBhW&?gu9W\"\"\\\$;)dh+Vr\",\"Qb9rjktait?0vj)i ;Z(,8`\",\"JW#`(uv(LqgxIFU\"\"UDG uy\",Ar*6t8a|lZpYm=\$,\"O:1+Fg^mq;pN'w6k.W^mFG+|\$A`{!d|*'iS,.\",j,\"p[Iy[+4GDgBE/*'][.N7|dt\$Aa?}bX\\}rd^U,bL'\\<y&1*v\",`[w6ZM)0 tM{jCm<h|,\"54Zn\"\"e(\",Qrl@G:dmHMgQq/i\$YjV@+'%,3b+6f2(:r@[-[%#Tyu8^?mP\\z8,6I<[A7.Ady\n" +
			"\"Tb_w>bM60gq.8m/{} KHpf+\"\"2c}!uC`u>*1WzVx-yG*BX\",4`lkD('vwn+'LGnp YzvF4%m[,<jE;tIB+EKW&^^}l,\"md^\"\"fob:rrl>rjjdHB@+W\$WwBfbFj.j\",;wpQxZRM<T'|zyQcj|O0,7u|:>>M)qcY.8tL/Z,\"E>LdX[;i[L3[ l)O,^bNNOyx77Y\",\"!Zb,ph,vonGWPps!>0_>a.)*<+1V\",\"Vr{2TqRt{M+eW,Jz\",\"))\"\",xn=\$UC6`w?}9,`cSM|ussF=FgGb\",\"9NmS>LL:`1!\"\"^lu&_qC6 )BNrybOnQr\",ic\\G2FmD*M9Kb^\n" +
			"b%V.,sqIIUGoPTd,H-PI{Qe\$2HvKPvtb,\"}{M/r.V&V\\S+RZ*[gAEIy 5cn8tL=`iHg\"\"{]!K1C{]xa\",6CY|HDb-Bz}Lxe-{{;zO-YU4'jd9&#Ucdi39;3kz^UxC[ilC,YP_@#,\"NG 3JkwkV:IKz.<\"\"P5W[)yZP\\UIs`ApO21mzNlO]7W\\Ssc\",g\\fj<| ,9il5 Sn8&Q:,\"fK}89^'iFb_YAI:Zqw\"\"75r>K?%\\K7+,>Ob(2':?} is&)hf){\",UEk^k>,*\n" +
			"EmAH:{CSN9k?hO_oW{83Us]k,.<4dl=8f)zl--.,u*5e19- `ri8a/e0z(07trw]MyzM/E<u,64bT2jMz_m6uA8Mhh),#|vf2),\":'Cobd>v@K:*z\"\"6UHpN ;&TJF,PPg\$ ;>#vMZ+E:\",\"x\$3LT[zZoK7vRr<;05UG mc,KY{;23jf:l_9_M}\",\"#,F7b`WzME]R%(=?`2C,ie|>9.GM8w|{\$+1R:E]!X.wF6`JjN\",quHZ[]8T2,F{\${I7n6-&XM]tT}+>Lpfe01YjT9LW_.,FFy&Z*').Z:Nq(,?>F^=Ydz !G9r\\f=H0pzJH9|9r<#&0zN;)76p;kE*8w\n" +
			"\"5k=Q]-7%,fl;rl'uA:a|Ho;'>-K1Y\"\"I\",?,FFS_Z90mO,,')Q>,vemf{a,\"DMFR+D:{b)40\"\"\$eQ{\",3pa!L4`g,|L>,k*?lhU62Wf;- Bc86|-pl_]SCm>pV?bYG)EsP[@&5,\"CO'1Py//\"\"G:\$r_Y-Ud<O:.\",.t\n" +
			"\"-8iD.FiZ|cDOb&Z<M0G932Mgq]U|YlDNOjioR_yfw,UGai}\",\"P /,=wfb\\0\\zbWH\\1bc/|^_.-ux@HKD>I>GU{W0/iZfD>YH\", S\$M]f+`-zn+*+%Q]@Ei7b>1EgcGc(@}>QlfO2.S:|CN%sz^,@/>i&bS,Wq679Dch=01Lk20cJNL9m^f,Hh,ibH#*\\),\"(q*1\"\"y{Mm!=g\",\"P0;8`mwU.FryrQla:|&HRAY!coURjVG\"\"|;\"\"I0cNY\",\"I6n/k!i&!}ynn*w;g\"\"]L?K@7&{,f@;zVC\",bGvVr\\).`|o@h,\"\"\"m>OXW\"\"h|A'r[_1)YFYH]O=q(@K?9\"\"9{KWqU*e3\"\n" +
			"c;j*\$ Ct*6R,:4SNW02/o@jZBprtLG79\$2!,qz=aW`R:gE&oPZf4R8bMb-@Ie{i{l,l\\kINbLIc,zZ\\Wz?u`DT{`.NQF_)E;1]yO@4J) `|z4i,T>{F:?,,\"U&PY?ya+O0Qg&@4^)LTs\"\"< 6W&?_U-`\",\"^u/>9%,f:\",\"Df\"\"[IBQv8V8E!WGGSsic^G\",4,\n" +
			"|DyTO07b/t)0#=5>>-90XFvqF1,<j,WHrrhPpz'2c*ql5V:A3uIX::;,!o`@FEy`^7`z:#Ub-'*,\"?wJ=M^kP\\Ie|`<*x-]D23T\"\"G\",\"@8i }`C)5DN(|0XGB2gcPxST[cl\$]H\"\"x'Yjz@.M='_bv5.S_\",x^62HWlP{<JNWgZy4;)vY3JkuNGa,4su*%Q`=3,7srW/+O5Y|lzNmFDVyUWbbI-h!%8/7@j\\(?SKdK6;QdF0wj,!917?A?n(se{DyEJZYG.<N29./w^HK810'MQ[! 9mX,\"xJAmo^{thFK1oU3gE_@3?(js=:\\\\D\"\"'pm%^q-fZvB4Et7*%\",\"`=d<WN,V>+ Jav[&`=\"\"\"\"w}=Q.FD([D=\"\"]^.N7S[z\""

	@Test
	fun readTest() {
		val t = readCsv(test1Csv.reader().buffered(), quote = '"').map { it.toList().toTypedArray() }.toList().toTypedArray()
		assertArrayEquals(t, test1Ary)
	}
	@Test
	fun writeTest() {
		assertArrayEquals(readCsv(test1Ary.toCsvString().reader().buffered(), quote = '"').map { it.toList().toTypedArray() }.toList().toTypedArray(), test1Ary)
	}
}
