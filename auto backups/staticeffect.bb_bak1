Graphics3D 640,480,32,1
SetBuffer BackBuffer()
camera=CreateCamera()
light=CreateLight()

wholestatic=LoadImage("static.png")

SeedRnd=MilliSecs()
While Not KeyDown( 1 )

RenderWorld

DrawBlockRect wholestatic,10,10,Rand(88),Rand(160),212,140

Flip
Wend
End