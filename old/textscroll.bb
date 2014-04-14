Graphics3D 640,480,32,2
SetBuffer BackBuffer() 

fntcourier=LoadFont ("Courier",20,True,False,False) 
SetFont fntcourier

redbackground=CreateImage(640,480)

SetBuffer ImageBuffer(redbackground)
Color 255,0,0

Rect 64,177,240,20
Rect 384,177,240,20
Rect 64,380,240,20
Rect 384,380,240,20
Color 255,255,255

SetBuffer BackBuffer()



;tx$=tx$+"SOMETHING ELSE "

textscroll1$=" TGS - NORTH     "
textscroll2$=" TGS - EAST      "
textscroll3$=" TGS - SOUTH     "
textscroll4$=" TGS - WEST      "
textscroll5$="BYPASS CAMERA    "
textscroll6$="SECURITY MON A   "
textscroll7$="SECURITY MON B   "
textscroll8$="     LIVE        "
textscroll9$="     TAPE        "

Dim tx$(4):tx$(1)="":tx$(2)="":tx$(3)="":tx$(4)=""
Dim scrollgo(4):scrollgo(1)=0:scrollgo(2)=0:scrollgo(3)=0:scrollgo(4)=0
Dim go#(4):go#(1)=8:go#(2)=8:go#(3)=8:go#(4)=8
Dim scr_scroll(4):scr_scroll(1)=CreateImage(640,25):scr_scroll(2)=CreateImage(640,25):scr_scroll(3)=CreateImage(640,25):scr_scroll(4)=CreateImage(640,25)
Dim tx_pos(4):tx_pos(1)=1:tx_pos(2)=1:tx_pos(3)=1:tx_pos(4)=1


While Not KeyDown(1)

If KeyHit(2) Then tx$(1)=tx$(1)+textscroll1$ : scrollgo(1)=1
If KeyHit(3) Then tx$(2)=tx$(2)+textscroll2$ : scrollgo(2)=1
If KeyHit(4) Then tx$(3)=tx$(3)+textscroll3$ : scrollgo(3)=1
If KeyHit(5) Then tx$(4)=tx$(4)+textscroll4$ : scrollgo(4)=1
If KeyHit(6) Then tx$(1)=tx$(1)+textscroll5$ : scrollgo(1)=1
If KeyHit(7) Then tx$(2)=tx$(2)+textscroll6$ : scrollgo(2)=1
If KeyHit(8) Then tx$(3)=tx$(3)+textscroll7$ : scrollgo(3)=1
If KeyHit(9) Then tx$(4)=tx$(4)+textscroll8$ : scrollgo(4)=1
If KeyHit(10) Then tx$(1)=tx$(1)+textscroll9$ : scrollgo(1)=1


DrawImage redbackground,0,0

change#=0.7;3

textspeed#=(-2*(change#/change#))
textstop#=(8*change#)
textspace#=(1.5*change#)

For i=1 To 4

If scrollgo(i)=1

go#(i)=go#(i)+textspace#
If go#(i)>=textstop#
	SetBuffer ImageBuffer(scr_scroll(i))
	go#(i)=0
	Text 229,0,Mid$(tx$(i),tx_pos(i),1)
	tx_pos(i)=tx_pos(i)+1 : If tx_pos(i) > Len(tx$(i)) Then tx_pos(i)=1 : scrollgo(i)=0 : tx$(i)=""
	SetBuffer BackBuffer()
EndIf
 
SetBuffer ImageBuffer(scr_scroll(i))
DrawBlock scr_scroll(i),textspeed#,0
SetBuffer BackBuffer()

EndIf ;If scrollgo=1


Next


DrawImage scr_scroll(1),64,177
DrawImage scr_scroll(2),384,177
DrawImage scr_scroll(3),64,380
DrawImage scr_scroll(4),384,380




Flip

Cls

Wend