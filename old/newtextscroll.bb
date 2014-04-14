Graphics3D 640,480,32,2
SetBuffer BackBuffer() 

fntcourier=LoadFont ("Courier",20,True,False,False) 
SetFont fntcourier

redbackground=CreateImage(640,480)

SetBuffer ImageBuffer(redbackground)
Color 100,0,0

Rect 64,177,240,20
Rect 384,177,240,20
Rect 64,380,240,20
Rect 384,380,240,20
Color 255,255,255

SetBuffer BackBuffer()

textscrollimg=LoadAnimImage("textscroll.png",240,20,0,9)

Dim scrollgo(4):scrollgo(1)=0:scrollgo(2)=0:scrollgo(3)=0:scrollgo(4)=0
Dim scr_scroll(4):scr_scroll(1)=CreateImage(240,20):scr_scroll(2)=CreateImage(240,20):scr_scroll(3)=CreateImage(240,20):scr_scroll(4)=CreateImage(240,20)
Dim scr_scroll2(4):scr_scroll2(1)=CreateImage(240,20):scr_scroll2(2)=CreateImage(240,20):scr_scroll2(3)=CreateImage(240,20):scr_scroll2(4)=CreateImage(240,20)

Dim scr_scrollwho(4):scr_scrollwho(1)=CreateImage(480,20):scr_scrollwho(2)=CreateImage(480,20):scr_scrollwho(3)=CreateImage(480,20):scr_scrollwho(4)=CreateImage(480,20)

Dim tx_pos(4):tx_pos(1)=304:tx_pos(2)=304:tx_pos(3)=304:tx_pos(4)=304
Dim tx_pos2(4):tx_pos2(1)=304:tx_pos2(2)=304:tx_pos2(3)=304:tx_pos2(4)=304

;for the third scroller (experiment)
Dim scr_scroll3(4):scr_scroll3(1)=CreateImage(240,20):scr_scroll3(2)=CreateImage(240,20):scr_scroll3(3)=CreateImage(240,20):scr_scroll3(4)=CreateImage(240,20)
Dim tx_pos3(4):tx_pos3(1)=304:tx_pos3(2)=304:tx_pos3(3)=304:tx_pos3(4)=304


While Not KeyDown(1)

If KeyHit(2)


If tx_pos(1)=304 ;If the first image holder is in its start position
	SetBuffer ImageBuffer(scr_scroll(1))
	DrawImage textscrollimg,0,0,0
	SetBuffer BackBuffer()
	scrollgo(1)=1
ElseIf tx_pos(1)<>304 And tx_pos2(1)=304 ;Otherwise, use the second image holder
	SetBuffer ImageBuffer(scr_scroll2(1))
	DrawImage textscrollimg,0,0,1
	SetBuffer BackBuffer()
	scrollgo(1)=2
	tx_pos2(1)=tx_pos(1)+240	
ElseIf tx_pos(1)<>304 And tx_pos3(1)=304
	SetBuffer ImageBuffer(scr_scroll3(1))
	DrawImage textscrollimg,0,0,5
	SetBuffer BackBuffer()
	
	scrollgo(1)=3
	tx_pos3(1)=tx_pos2(1)+240
EndIf

EndIf


DrawImage redbackground,0,0


change#=0.7
textspeed#=(-6*(change#/change#))

For i=1 To 4

If scrollgo(i)=1 ;If the first image holder has been told to scroll
	tx_pos(i)=tx_pos(i)+textspeed# ;move the first image along
	If tx_pos3(i)<>304 Then tx_pos3(i)=tx_pos3(i)+textspeed# ;and move the third one along if it's not in it's start position
	If tx_pos(i) <= 64 Then scrollgo(i)=0 : tx_pos2(i)=304 ;and if it reaches it's end, stop it
	
ElseIf scrollgo(i)=2 ;Otherwise, if the second image holder has been told to scroll
	tx_pos2(i)=tx_pos2(i)+textspeed# ;move it onwards
	If tx_pos(i)<>304 Then tx_pos(i)=tx_pos(i)+textspeed# ;if the first one is not in start position, move it along
	If tx_pos2(i) <= 64 Then scrollgo(i)=0 : tx_pos3(i)=304
	
ElseIf scrollgo(i)=3 ;Third scroller has been told to scroll
	tx_pos3(i)=tx_pos3(i)+textspeed#
	If tx_pos2(i)<>304 Then tx_pos2(i)=tx_pos2(i)+textspeed# ;move the second one away
	If tx_pos3(i) <= 64 Then scrollgo(i)=0 : tx_pos(i)=304
	
EndIf ;If scrollgo=1

SetBuffer ImageBuffer(scr_scrollwho(i))
Cls
DrawImage scr_scroll(i),tx_pos(i),0 : DrawImage scr_scroll2(i),tx_pos2(i),0 ;: DrawImage scr_scroll3(i),tx_pos3(i),0
SetBuffer BackBuffer()

Next

DrawImageRect scr_scrollwho(1),64,177,64,0,240,20


Flip

Cls

Wend