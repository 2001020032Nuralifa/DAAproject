import turtle
import math 

wn = turtle.Screen()
wn.bgcolor("black")
wn.title("Smart Courier")
wn.setup(700,700)

#Register shapes
turtle.register_shape("kurir-kanan.gif")
turtle.register_shape("kurir-kiri.gif")
turtle.register_shape("kurir-bawah.gif")
turtle.register_shape("kurir-atas.gif")
turtle.register_shape("bendera.gif")
turtle.register_shape("tembok.gif")

#Create Pen
class Pen(turtle.Turtle):
	def __init__(self):
		turtle.Turtle.__init__(self)
		self.shape("square")
		self.color("white")
		self.penup()
		self.speed(0)

class Courier(turtle.Turtle):					
	def __init__(self):
		turtle.Turtle.__init__(self)
		self.shape("kurir-kanan.gif")
		self.color("blue")
		self.penup()
		self.speed(0)
		self.gold = 0

	def go_up(self):
		#calculate the spot to move to
		move_to_x = courier.xcor()
		move_to_y = courier.ycor() + 24
		self.shape("kurir-atas.gif")

		#check if the space has a wall
		if (move_to_x, move_to_y) not in walls:
			self.goto(move_to_x, move_to_y)

	def go_down(self):
		#calculate the spot to move to
		move_to_x=courier.xcor()
		move_to_y=courier.ycor() - 24
		self.shape("kurir-bawah.gif")

		#check if the space has a wall
		if (move_to_x, move_to_y) not in walls:
			self.goto(move_to_x, move_to_y)

	def go_left(self):
		#calculate the spot to move to
		move_to_x=courier.xcor() - 24
		move_to_y=courier.ycor()
		self.shape("kurir-kiri.gif")

		#check if the space has a wall
		if (move_to_x, move_to_y) not in walls:
			self.goto(move_to_x, move_to_y)


	def go_right(self):
		#calculate the spot to move to
		move_to_x=courier.xcor() + 24
		move_to_y=courier.ycor() 
		self.shape("kurir-kanan.gif")

		#check if the space has a wall
		if (move_to_x, move_to_y) not in walls:
			self.goto(move_to_x, move_to_y)

	def is_collision(self, other):
		a=self.xcor()-other.xcor()
		b=self.ycor()-other.ycor()
		distance=math.sqrt((a**2)+(b**2))

		if distance<5:
			return True
		else:
			return False

class Flag(turtle.Turtle):
	def __init__(self, x, y):
		turtle.Turtle.__init__(self)
		self.shape("bendera.gif")
		self.color("gold")
		self.penup()
		self.speed(0)
		self.gold = 100
		self.goto(x, y)

	def destroy(self):
		self.goto(2000, 2000)
		self.hideturtle()

#Create levels list
levels=[""]

#Define first level
level_1 = [
"XXXXXXXXXXXXXXXXXXXXXXXXXX",
"XP  XXXXXXX         XXXXXX",
"X   XXXXXXX  XXXXXX XXXXXX",
"X        XX  XXXXXX XXXXXX",
"X        XX  XXX        XX",
"XXXXXX   XX  XXX        XX",
"XXXXXX   XX  XXXXX  XXXXXX",
"XXXXXX   XX    XXX  XXXXXX",
"X  XXX        XXXF  XXXXXX",
"X  XXX       XXXXXXXXXXXXX",
"X            XXXXXXXXXXXXX",
"XXXXXXX	   XXXXXXXXXXXX",
"XXXXXXXX          XXXXXXXX",
"XXXXXXXX          XXXXXXXX",
"XXX   XXXXXXXXXX 		XXX",
"XXX 					  X",
"XXF          XXXXXXXXXXXXX",
"XXXXXXXXXX   XXXXXXXXXXXXX",
"XXXXXXXXXX               X",
"XX   XXXXX               X",
"XX   XXXXXXXXXXXXX   XXXXX",
"XX   FXXXXXXXXXXXX   XXXXX",
"XX                       X",
"XX  					  X",
"XXXXXXXXXXXXXXXXXXXXXXXXXX"
]

#Add treasure list
flags = []

#Add maze to maze list
levels.append(level_1)

#Create Level Setup Function
def setup_smart(level):
	for y in range(len(level)):
		for x in range(len(level[y])):
			#Get the character at each x,y coordinate
			#Note the order of y and x in the next line
			character = level[y][x]
			#Calculate the screen x,y coordinate
			screen_x = -288 + (x*24)
			screen_y = 288 - (y*24)

			#check if it is an X (representing a wall)
			if character == "X":
				pen.goto(screen_x, screen_y)
				pen.shape("tembok.gif")
				pen.stamp()
				#Add coordinates to wall list
				walls.append((screen_x, screen_y))

			#check if it is a P (representing the player)
			if character == "P":
				courier.goto(screen_x, screen_y)

			#check if it is a T (representing the Flag)
			if character == "F":
				flags.append(Flag(screen_x, screen_y)) 

#create class instances
pen = Pen()
courier = Courier()

#Create wall coordinate list
walls = []

#Set up the level
setup_smart(levels[1])

#Keyboard Binding
turtle.listen()
turtle.onkey(courier.go_left,"Left")
turtle.onkey(courier.go_right,"Right")
turtle.onkey(courier.go_up,"Up")
turtle.onkey(courier.go_down,"Down")

#Turn off screen updates
wn.tracer(0)

#main game loop
while True:
	#check for courier collision with flag
	#iterate through flag list
	for flag in flags:
		if courier.is_collision(flag):
			#add the flag gold to the courier gold
			courier.gold += flag.gold
			print ("Courier Gold: {}".format(courier.gold))
			#destroy the flag
			flag.destroy()
			#remove the flag from the flags list
			flags.remove(flag)
	#update screen
	wn.update()