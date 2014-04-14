Graphics3D 640,480
SetBuffer BackBuffer()

textlayer = CreateImage(640,480)

camera=CreateCamera()
light=CreateLight()

; camera position values (unimportant)
cx#=0
cy#=0
cz#=0

PositionEntity camera,cx#,cy#,cz#

textcursorx#=cx#
textcursory#=cy#
textcursorz#=cz#+1

textcursor=CreateSprite()
PositionEntity textcursor,textcursorx#,textcursory#,textcursorz#+1
ScaleSprite textcursor,0.045,0.062

textleftoffset=30 ;pixel gap to leave between the left margin (multiple of 10)

;Images that will be used in the text
mrupic=LoadImage("mrupic.bmp")
mfsmpic=LoadImage("mfsmpic.bmp")
;Text variables and actual game text
fntcourier=LoadFont ("Courier",20,True,False,False) 
SetFont fntcourier
normaltypepause=90
quicktypepause=30
typepause=normaltypepause
linecount=143
Dim msg$(linecount,1)
msg(0,0)=" " : msg (0,1)="DIAL"
msg(1,0)="(CONNECTED)" : msg(1,1)=0
msg(2,0)="" : msg(2,1)="PAU4"
msg(3,0)="* H2 - PLUS VERSION 2.0 *" : msg(3,1)=20
msg(4,0)="LICENSE #0480-0171-HCK2" : msg(4,1)=40
msg(5,0)="" : msg(5,1)="PAU1"
msg(6,0)="LOGON PLEASE:" : msg(6,1)=80
msg(7,0)="" : msg(7,1)="LOGON"
msg(8,0)="              " : msg(8,1)=80
msg(9,0)="" : msg(9,1)="RESETP"
msg(10,0)="" : msg(10,1)="PAU2"
msg(11,0)="REQUEST RECORDED" : msg(11,1)=100
msg(12,0)="" : msg(12,1)="PAU1"
msg(13,0)="ONE MOMENT, PLEASE" : msg(13,1)=120
msg(14,0)="" : msg(14,1)="PAU6"
msg(15,0)="THANK YOU FOR WAITING" : msg(15,1)=160
msg(16,0)="" : msg(16,1)="PAU1"
msg(17,0)="" : msg(17,1)="CLS"
msg(18,0)="      Welcome To Actisource" : msg(18,1)=0
msg(19,0)="The International Computer Hotline" : msg(19,1)=20
msg(20,0)="     Copyright (C) 1986-2003" : msg(20,1)=40
msg(21,0)="     Actisource Incorperated" : msg(21,1)=60
msg(22,0)="       all rights reserved" : msg(22,1)=80
msg(23,0)="****** WHAT'S NEW THIS WEEK *******" : msg(23,1)=120
msg(24,0)="1. This week's TOP 10" : msg(24,1)=160
msg(25,0)="2. Reviews of bestselling software" : msg(25,1)=180
msg(26,0)="3. Interview with Brad Fregger..." : msg(26,1)=200
msg(27,0)="   Activision Producer" : msg(27,1)=220
msg(28,0)="4. Tips from p" : msg(28,1)=240
msg(29,0)="" : msg(29,1)="PAU6"
msg(30,0)="TRANSMISSION INTERRUPTED" : msg(30,1)=280
msg(31,0)="" : msg(31,1)="PAU1"
msg(32,0)="PLEASE STAND BY" : msg(32,1)=300
msg(33,0)="" : msg(33,1)="PAU4"
msg(34,0)="" : msg(34,1)="CLS"
msg(35,0)="" : msg(35,1)="SETQUICKP"
msg(36,0)="Greetings from the Government of the" : msg(36,1)=0
msg(37,0)="     United States of America" : msg(37,1)=20
msg(38,0)="" : msg(38,1)="PAU3"
msg(39,0)="Since you have been recognized as" : msg(39,1)=60
msg(40,0)="the world's leading authority on" : msg(40,1)=80
msg(41,0)="computer security systems, the" : msg(41,1)=100
msg(42,0)="Central Intelligence Agency wishes to" : msg(42,1)=120
msg(43,0)="enlist you in its efforts to combat" : msg(43,1)=140
msg(44,0)="International terrorism." : msg(44,1)=160
msg(45,0)="" : msg(45,1)="PAU2"
msg(46,0)="Please press (RETURN) to indicate" : msg(46,1)=200
msg(47,0)="your acceptance of the assignment." : msg(47,1)=220
msg(48,0)="" : msg(48,1)="PAU1"
msg(49,0)="Your full cooperation would be" : msg(49,1)=240
msg(50,0)="deeply appreciated. However, should" : msg(50,1)=260
msg(51,0)="you refuse or fail, the C.I.A. will" : msg(51,1)=280
msg(52,0)="disavow any knowledge of your" : msg(52,1)=300
msg(53,0)="participation in this action." : msg(53,1)=320
msg(54,0)="" : msg(54,1)="PAU1"
msg(55,0)="      Sincerely," : msg(55,1)=360
msg(56,0)="      (IDENTITY CLASSIFIED)" : msg(56,1)=380
msg(57,0)="      Director of Special Agents" : msg(57,1)=400
msg(58,0)="      Washington, D.C." : msg(58,1)=420
msg(59,0)="" : msg(59,1)="WAITRETURN"
msg(60,0)="" : msg(60,1)="CLS"
msg(61,0)="OPERATION BRIEF:" : msg(61,1)=0
msg(62,0)="" : msg(62,1)="PAU1"
msg(63,0)="Intelligence reports indicate that" : msg(63,1)=40
msg(64,0)="Alexander Cherkazov, a top Russian" : msg(64,1)=60
msg(65,0)="scientist and political strategist" : msg(65,1)=80
msg(66,0)="has formulated a simple, yet" : msg(66,1)=100
msg(67,0)="devastating plan to overthrow the" : msg(67,1)=120
msg(68,0)="Government of the United States." : msg(68,1)=140
msg(69,0)="" : msg(69,1)="PAU1"
msg(70,0)="It is believed that the contents of" : msg(70,1)=160
msg(71,0)="this 'Doomsday Paper' could shift" : msg(71,1)=180
msg(72,0)="the balance of power and jeopardize" : msg(72,1)=200
msg(73,0)="the entire free world." : msg(73,1)=220
msg(74,0)="" : msg(74,1)="PAU2"
msg(75,0)="The only known copies of this plan" : msg(75,1)=260
msg(76,0)="are safely hidden within a vault" : msg(76,1)=280
msg(77,0)="inside a military complex in Siberia." : msg(77,1)=300
msg(78,0)="" : msg(78,1)="PAU1"
msg(79,0)="By obtaining a copy of this plan," : msg(79,1)=320
msg(80,0)="the C.I.A. could implement safeguards" : msg(80,1)=340
msg(81,0)="against such a scheme." : msg(81,1)=360
msg(82,0)="(RETURN)" : msg(82,1)=380
msg(83,0)="" : msg(83,1)="WAITRETURN"
msg(84,0)="" : msg(84,1)="CLS"
msg(85,0)="Our agents have been able to place 3" : msg(85,1)=0
msg(86,0)="Mobile Remote Units (MRU's) within" : msg(86,1)=20
msg(87,0)="the military complex. Each MRU has" : msg(87,1)=40
msg(88,0)="been equipped with a Remote Optical" : msg(88,1)=60
msg(89,0)="Analyzer (ROA) for the express" : msg(89,1)=80
msg(90,0)="purpose of gathering intelligence." : msg(90,1)=100
msg(91,0)="" : msg(91,1)="MRUPIC"
msg(92,0)="Remove a copy of the document marked" : msg(92,1)=250
msg(93,0)="'CLASSIFIED' from the vault and" : msg(93,1)=270
msg(94,0)="deliver it to our agent waiting at" : msg(94,1)=290
msg(95,0)="the entrance. Pieces of the vault's" : msg(95,1)=310
msg(96,0)="combination are hidden in 4 filing" : msg(96,1)=330
msg(97,0)="cabinets. The access code to one of" : msg(97,1)=350
msg(98,0)="the cabinets is 'RED 7'." : msg(98,1)=370
msg(99,0)="(RETURN)" : msg(99,1)=390
msg(100,0)="" : msg(100,1)="WAITRETURN"
msg(101,0)="" : msg(101,1)="CLS"
msg(102,0)="" : msg(102,1)="MFSMPIC"
msg(103,0)="A Multi-Function Switching Matrix" : msg(103,1)=240
msg(104,0)="(MFSM) has been planted in the" : msg(104,1)=260
msg(105,0)="building to aid you in avoiding" : msg(105,1)=280
msg(106,0)="detection. Familiarize yourself with" : msg(106,1)=300
msg(107,0)="the construction of the MFSM by" : msg(107,1)=320
msg(108,0)="identifying sections of the block" : msg(108,1)=340
msg(109,0)="diagram." : msg(109,1)=360
msg(110,0)="(RETURN)" : msg(110,1)=380
msg(111,0)="" : msg(111,1)="WAITRETURN"
msg(112,0)="" : msg(112,1)="CLEARLOWER"
msg(113,0)="INPUT FROM SURVEILLANCE CAMERAS:" : msg(113,1)=240
msg(114,0)="" : msg(114,1)="Q2"
msg(115,0)="                                 " : msg(115,1)=240
msg(116,0)="" : msg(116,1)="CHECKANSWER"
msg(117,0)="INPUT FROM PRE-RECORDED TAPES:  " : msg(117,1)=260
msg(118,0)="" : msg(118,1)="Q3"
msg(119,0)="                                 " : msg(119,1)=260
msg(120,0)="" : msg(120,1)="CHECKANSWER"
msg(121,0)="CAMERA BYPASS SWITCH:           " : msg(121,1)=280
msg(122,0)="" : msg(122,1)="Q6"
msg(123,0)="                                 " : msg(123,1)=280
msg(124,0)="" : msg(124,1)="CHECKANSWER"
msg(125,0)="OUTPUT TO SECURITY MONITORS:    " : msg(125,1)=300
msg(126,0)="" : msg(126,1)="Q4"
msg(127,0)="                                 " : msg(127,1)=300
msg(128,0)="" : msg(128,1)="CHECKANSWER"
msg(129,0)="INPUT FROM MFSM PANEL CONTROLS: " : msg(129,1)=320
msg(130,0)="" : msg(130,1)="Q1"
msg(131,0)="                                 " : msg(131,1)=320
msg(132,0)="" : msg(132,1)="CHECKANSWER"
msg(133,0)="PHNORDMAN VIDEO MATRIX (PVM):   " : msg(133,1)=340
msg(134,0)="" : msg(134,1)="Q7"
msg(135,0)="                                 " : msg(135,1)=340
msg(136,0)="" : msg(136,1)="CHECKANSWER"
msg(137,0)="OUTPUT TO MFSM DISPLAY:         " : msg(137,1)=360
msg(138,0)="" : msg(138,1)="Q5"
msg(139,0)="                                 " : msg(139,1)=360
msg(140,0)="" : msg(140,1)="CHECKANSWER"
msg(141,0)="MFSM REVIEW COMPLETE-   (RETURN) " : msg(141,1)=380
msg(142,0)="" : msg(142,1)="WAITRETURN"


time=MilliSecs()

;---------------------
;Game Loop
;---------------------
While Not KeyDown(1)

If setstartline=0
	a=0 : b=101
	setstartline=1
EndIf
Gosub printtext

UpdateWorld
RenderWorld

DrawImage textlayer,textleftoffset-2,0

Flip
Wend

End

;SUBROUTINE DEALING WITH THE TEXT
.printtext

If MilliSecs()>time+typepause
	If needtoreseta=1 : a=0 : needtoreseta=0 : EndIf
	If msg(b,1)="CLS"
		FlushKeys
		SetBuffer ImageBuffer(textlayer) : Cls : SetBuffer BackBuffer() : a=0 : b=b+1 : Delay 500
	ElseIf msg(b,1)="DIAL"
		Delay 5000 : a=0 : b=b+1 ;Play the dialing sounds
	ElseIf Left$(msg(b,1),3)="PAU"
		pausetime# = Right$(msg(b,1),1) : Delay pausetime#*1000 : b=b+1
	ElseIf msg(b,1)="LOGON"
		Print ""
		Print ""
		Print ""
		Print ""
		HideEntity textcursor
		UpdateWorld : RenderWorld : DrawImage textlayer,textleftoffset-2,0 : Flip
		logonname$=Input(String$(" ",(textleftoffset/10)-1)+"LOGON PLEASE: ")
		ShowEntity textcursor
		logonname$=Upper$(logonname$)
		typepause=quicktypepause
		msg(8,0)=msg(8,0)+logonname$
		a=14
		b=b+1
	ElseIf msg(b,1)="Q2"
		For l=1 To 12
			Print ""
		Next
		HideEntity textcursor
		UpdateWorld : RenderWorld : DrawImage textlayer,textleftoffset-2,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-1)+"INPUT FROM SURVEILLANCE CAMERAS: ")
		ShowEntity textcursor
		answer$=Upper$(answer$)
		trueanswer$="2"
		Gosub checkanswer
	ElseIf msg(b,1)="Q3"
		For l=1 To 13
			Print ""
		Next
		HideEntity textcursor
		UpdateWorld : RenderWorld : DrawImage textlayer,textleftoffset-2,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-1)+"INPUT FROM PRE-RECORDED TAPES:   ")
		ShowEntity textcursor
		answer$=Upper$(answer$)
		trueanswer$="3"
		Gosub checkanswer
	ElseIf msg(b,1)="Q6"
		For l=1 To 14
			Print ""
		Next
		HideEntity textcursor
		UpdateWorld : RenderWorld : DrawImage textlayer,textleftoffset-2,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-1)+"CAMERA BYPASS SWITCH:            ")
		ShowEntity textcursor
		answer$=Upper$(answer$)
		trueanswer$="6"
		Gosub checkanswer
	ElseIf msg(b,1)="Q4"
		For l=1 To 15
			Print ""
		Next
		HideEntity textcursor
		UpdateWorld : RenderWorld : DrawImage textlayer,textleftoffset-2,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-1)+"OUTPUT TO SECURITY MONITORS:     ")
		ShowEntity textcursor
		answer$=Upper$(answer$)
		trueanswer$="4"
		Gosub checkanswer
	ElseIf msg(b,1)="Q1"
		For l=1 To 16
			Print ""
		Next
		HideEntity textcursor
		UpdateWorld : RenderWorld : DrawImage textlayer,textleftoffset-2,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-1)+"INPUT FROM MFSM PANEL CONTROLS:  ")
		ShowEntity textcursor
		answer$=Upper$(answer$)
		trueanswer$="1"
		Gosub checkanswer
	ElseIf msg(b,1)="Q7"
		For l=1 To 17
			Print ""
		Next
		HideEntity textcursor
		UpdateWorld : RenderWorld : DrawImage textlayer,textleftoffset-2,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-1)+"PHNORDMAN VIDEO MATRIX (PVM):    ")
		ShowEntity textcursor
		answer$=Upper$(answer$)
		trueanswer$="7"
		Gosub checkanswer
	ElseIf msg(b,1)="Q5"
		For l=1 To 18
			Print ""
		Next
		HideEntity textcursor
		UpdateWorld : RenderWorld : DrawImage textlayer,textleftoffset-2,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-1)+"OUTPUT TO MFSM DISPLAY:          ")
		ShowEntity textcursor
		answer$=Upper$(answer$)
		trueanswer$="5"
		Gosub checkanswer
	ElseIf msg(b,1)="RESETP"
		typepause=normaltypepause
		a=0 : b=b+1
	ElseIf msg(b,1)="CHECKANSWER"
		If answerwrong=0
			a=0 : b=b+1
		Else
			SetBuffer ImageBuffer(textlayer) : Color 0,0,0 : Rect 0,msg(b-1,1),640,20,1 : Color 255,255,255 : SetBuffer BackBuffer()
			Delay 500 : a=0 : b=b-3
		EndIf
	ElseIf msg(b,1)="SETQUICKP"
		typepause=quicktypepause
		a=0 : b=b+1
	ElseIf msg(b,1)="MRUPIC"
		HideEntity textcursor
		picnum=0
		Repeat
		picnum=picnum+1
			SetBuffer ImageBuffer(textlayer)
			DrawBlockRect mrupic,textleftoffset,140,0,0,500,picnum ;Image is 500 X 90 pixels
			SetBuffer BackBuffer()
			UpdateWorld : RenderWorld : DrawImage textlayer,textleftoffset-2,0 : Flip
		Until picnum=90
		a=0 : b=b+1
		ShowEntity textcursor
	ElseIf msg(b,1)="MFSMPIC"
		HideEntity textcursor
		picnum=0
		Repeat
		picnum=picnum+1
			SetBuffer ImageBuffer(textlayer)
			DrawBlockRect mfsmpic,textleftoffset-10,10,0,0,532,picnum ;Image is 532 X 214 pixels
			SetBuffer BackBuffer()
			UpdateWorld : RenderWorld : DrawImage textlayer,textleftoffset-2,0 : Flip
		Until picnum=214
		a=0 : b=b+1
		ShowEntity textcursor
	ElseIf msg(b,1)="WAITRETURN"	
		Repeat
		Until KeyDown(28)=True
		a=0
		b=b+1
		FlushKeys
	ElseIf msg(b,1)="CLEARLOWER"
	SetBuffer ImageBuffer(textlayer) : Color 0,0,0 : Rect 0,225,640,300,1 : Color 255,255,255 : SetBuffer BackBuffer() : a=0 : b=b+1 : Delay 500
	Else
		If a>Len(msg(b,0))
		a=0
		If b<linecount
			b=b+1
		EndIf
		EndIf
		a=a+1
		time=MilliSecs()
	EndIf
EndIf

SetBuffer ImageBuffer(textlayer)
	Text 0,msg(b,1),Left$(msg(b,0),a)
SetBuffer BackBuffer()

If msg(b,0) <> "" And msg(b,1)<>Yline# ;Keep the cursor at the last Y position it was at
	yLine#=msg(b,1)
EndIf

If msg(b,0) = "" ;Control the X position of the cursor
	If msg(b-1,0)=""
		a=Len(msg(b-2,0)+1)
	Else
		a=Len(msg(b-1,0)+1)
	EndIf
	needtoreseta=1
EndIf

textcursory#=1.439-((yline#/20)*0.125)
	If b=0
		textcursorx#=-1.951+((a+((textleftoffset/10)-1))*0.0872)
	Else
		textcursorx#=-1.951+((a-1+((textleftoffset/10)-1))*0.0872)
	EndIf
PositionEntity textcursor,textcursorx#,textcursory#,textcursorz#+1

Return

.checkanswer
	If answer$=trueanswer$
		msg(b+1,0)="                                 "
		msg(b+1,0)=msg(b+1,0)+answer$
		answerwrong=0
		a=33
		b=b+1
	Else
		SetBuffer ImageBuffer(textlayer) : Color 0,0,0 : Rect 0,msg(b-1,1),640,20,1 : Color 255,255,255 : SetBuffer BackBuffer()
		msg(b+1,0)="INCORRECT RESPONSE"
		answerwrong=1
		a=0
		b=b+1
	EndIf
Return