Graphics 640,480


arrwidth=38
arrheight=25

Dim bitmap(arrwidth,arrheight)

;Loading images into array
bmp=LoadImage("bitmapcopy.bmp")
For i=0 To arrwidth
	For v=0 To arrheight
		bitmap(i,v)=ReadPixel(i-1,v-1,ImageBuffer(bmp))
		
		
		If bitmap(i,v)=-1 Then Color 255,255,255
		If bitmap(i,v)=-65536 Then Color 255,255,255
		If bitmap(i,v)=-16777216 Then Color 0,255,0
		Rect i*4,v*4,10,10,1
	Next
Next