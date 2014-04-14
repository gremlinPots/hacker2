;Creating the building

ClearTextureFilters

;Loading Textures
fp1tex=LoadTexture("fp1.png")
fp2tex=LoadTexture("fp2.png")
fp4tex=LoadTexture("fp4.png")
fp6tex=LoadTexture("fp6.png")
fp7tex=LoadTexture("fp7.png")
fp8tex=LoadTexture("fp8.png")
fp11tex=LoadTexture("fp11.png")
fp12tex=LoadTexture("fp12.png")
fp13tex=LoadTexture("fp13.png")
;
wa1tex=LoadTexture("wa1.png")


;Floor (corridor)
;Horizontals
f1=CreateFloor(3,-3,3,2);f1=CreateFloor(3,-3,33,2)
f2=CreateFloor(5,-12,8,2)
f3=CreateFloor(5,-17,8,2)
f4=CreateFloor(29,-17,5,2)
f5=CreateFloor(29,-12,5,2)
;Verticals
f6=CreateFloor(3,-5,2,16)
f7=CreateFloor(13,-5,2,16)
f8=CreateFloor(17,-5,2,16)
f9=CreateFloor(27,-5,2,16)
f10=CreateFloor(34,-5,2,16)

f11=CreateFloor(12,-3,8,2)
f12=CreateFloor(22,-3,8,2)
f13=CreateFloor(33,-3,3,2)

;Fronts (walls facing the camera)
;top row
f14=CreateFront(3,-3,3)
f15=CreateFront(12,-3,4)
f16=CreateFront(16,-3,4)
f17=CreateFront(22,-3,5) ; Safe
f18=CreateFront(27,-3,3)
f19=CreateFront(33,-3,3)
;next row down
f20=CreateFront(5,-5,4) ; Cabinet (3)
f21=CreateFront(9,-5,4.1)
f22=CreateFront(19,-5,4)
f23=CreateFront(23,-5,4.1)
f24=CreateFront(29,-5,5.1) ; Cabinet (1)
;next row down
f25=CreateFront(5,-9,4)
f26=CreateFront(9,-9,4.1)
f27=CreateFront(19,-9,4)
f28=CreateFront(23,-9,4.1)
;next row down
f29=CreateFront(5,-12,3)
f30=CreateFront(10,-12,3.1)
f31=CreateFront(19,-13,4)
f32=CreateFront(23,-13,4.1) ; Cabinet (2)
f33=CreateFront(29,-12,5.1)
;next row down
f34=CreateFront(5,-14,4)
f35=CreateFront(9,-14,4.1) ; Cabinet (4)
;next row down
f36=CreateFront(31,-17,3.1)
f37=CreateFront(5,-17,3)

;Door walls
;lhs wall
f38=CreateDoor(3,-3,0.1,6,3.6,0.8,1) ;size 6
f39=CreateDoor(3,-9,0.1,5,1.6,0.8,1) ;size 5
f40=CreateDoor(3,-14,0.1,5,1.6,0.8,1) ;size 5
f41=CreateDoor(3,-19,0.1,2,0,0,0) ;size 2 (no door)
;---------------------
;lhs of first room row
f42=CreateDoor(5,-5,0.1,4,1.6,0.8,1) ;size 4
f43=CreateDoor(5,-9,0.1,3,1.6,0.8,1) ;size 3
f44=CreateDoor(5,-14,0.1,3,1.6,0.8,1) ;size 3
f45=CreateDoor(5,-19,0.1,2,0,0,0) ;size 2 (no door)
;middle walls of first room row
f46=CreateDoor(9,-5,0.1,4,1.6,0.8,1) ;size 4
f47=CreateDoor(9,-9,0.1,3,1.6,0.8,1) ;size 3
f48=CreateDoor(9,-14,0.1,3,1.6,0.8,1) ;size 3
;rhs of first room row
f49=CreateDoor(13,-5,0.1,4,1.6,0.8,1) ;size 4
f50=CreateDoor(13,-9,0.1,3,1.6,0.8,1) ;size 3
f51=CreateDoor(13,-14,0.1,3,1.6,0.8,1) ;size 3
;---------------------
;lhs and rhs of second room row
f52=CreateDoor(15,-5,2,4,0,0,0) ;size 4 (fake door)
f53=CreateDoor(15,-9,2,5,0,0,0) ;size 5 (fake door)
f54=CreateDoor(15,-14,2,5,0,0,0) ;size 5 (fake door)
;---------------------
;lhs of third room row
f55=CreateDoor(19,-5,0.1,4,1.6,0.8,1) ;size 4
f56=CreateDoor(19,-9,0.1,4,1.6,0.8,1) ;size 4
f57=CreateDoor(19,-13,0.1,5,2.6,0.8,1) ;size 5
;middle walls of third row
f58=CreateDoor(23,-5,0.1,4,1.6,0.8,1) ;size 4
f59=CreateDoor(23,-9,0.1,4,1.6,0.8,1) ;size 4
f60=CreateDoor(23,-13,0.1,5,2.6,0.8,1) ;size 5
;rhs of third room row
f61=CreateDoor(27,-5,0.1,4,1.6,0.8,1) ;size 4
f62=CreateDoor(27,-9,0.1,4,1.6,0.8,1) ;size 4
f63=CreateDoor(27,-13,0.1,5,2.6,0.8,1) ;size 5
;---------------------
;lhs of forth room row
f64=CreateDoor(29,-5,0.1,4,1.6,0.8,1) ;size 4
f65=CreateDoor(29,-9,0.1,3,0,0,0) ;size 3 (fake door)
;rhs of forth room row
f66=CreateDoor(34,-5,0.1,4,1.6,0.8,1) ;size 4
f67=CreateDoor(34,-9,0.1,3,0,0,0) ;size 3 (fake door)
f68=CreateDoor(34,-14,0.1,3,0,0,0) ;size 3 (fake door)
f69=CreateDoor(34,-19,0.1,2,0,0,0) ;size 2 (no door)
;---------------------
;rhs wall
f70=CreateDoor(36,-3,0.1,6,3.6,0.8,1) ;size 6
f71=CreateDoor(36,-9,0.1,5,1.6,0.8,1) ;size 5
f72=CreateDoor(36,-14,0.1,5,1.6,0.8,1) ;size 5
f73=CreateDoor(36,-19,0.1,2,0,0,0) ;size 2 (no door)

;Setting EntityFX (object are full bright)
;EntityFX f42,1
;EntityFX f43,1
;EntityFX f44,1
;EntityFX f45,1

;Texturing
;Corridor floors
EntityTexture f1,fp1tex
EntityTexture f2,fp2tex
EntityTexture f3,fp2tex
EntityTexture f4,fp4tex
EntityTexture f5,fp4tex
EntityTexture f6,fp6tex
EntityTexture f7,fp7tex
EntityTexture f8,fp8tex
EntityTexture f9,fp6tex
EntityTexture f10,fp7tex
EntityTexture f11,fp11tex
EntityTexture f12,fp12tex
EntityTexture f13,fp13tex
;Corridor walls

EntityTexture f38,wa1tex; Type 6
EntityTexture f39,wa1tex; Type 5
EntityTexture f42,wa1tex; Type 4
EntityTexture f43,wa1tex; Type 3