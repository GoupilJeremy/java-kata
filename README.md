# java-kata

## Leap year

`level: *`

Write a function that returns true or false depending on whether its input is a leap year or not. A leap year is divisible by 4, but is not otherwise divisible by 100, unless it is also divisible by 400.

## FizzBuzz

`level: *`, `emerging design`, `red-green-refactor`

For a given natural number greater than zero, you must return:
- « Fizz » if the number is divisible by 3
- « Buzz » if the number is divisible by 5
- « FizzBuzz » if the number is divisible by 3 and by 5
- The same number if no other requirement is fulfilled

## Fraction

`level: * *`, `baby steps`, `immutability`, `third-part library`

Write a program that implements the addition of fractions. A Fraction is a value object, once created it cannot be mutated. The `add method must return a new Fraction instance. Fractions must be reduced.

An implementation of the greatest common divisor (GCD) is provided:
```
static int gcd(int a, int b) { 
  return b == 0 ? a : gcd(b, a % b); 
}
```

## Greeting

`level: * * *`, `emerging design`

https://github.com/testdouble/contributing-tests/wiki/Greeting-Kata


## Coffee machine

`level: * * *`, `hexagonal architecture`, `test doubles`, `emerging design`

https://simcap.github.io/coffeemachine/

## Bank

`level: * * *`

https://github.com/sandromancuso/Bank-kata

## Tennis

`level: * * * *`

A tennis match is composed of points, games and sets. Below are exposed the rules of counting points within a game.
Your task is to implement this logic and expose a method which returns the current score as a string for display.

- Each player can have either of these points in one game: 0, 15, 30 and 40.
- If you have 40 and you win the ball, you win the game.
- However:
  - If both have 40 the players are "deuce".
  - If the game is in deuce, the winner of a ball will have "advantage".
  - If the player with advantage wins the ball he wins the game.
  - If the player without advantage wins they are back at deuce.

## Bowling

`level: * * * * *`, `inside-out`, `outside-in`, `emerging design`, `test recycling`

The two players play concurrently and for each player, the game consists of 10 frames.

 In each frame the player has two rolls to knock down 10 pins. The score for the frame is the total number of pins knocked down. 

A strike (`X`) is when the player knocks down all 10 pins on his first roll. In case of a strike, the player receives 10 points plus the sum of the points of the next two rolls as a bonus (these two rolls can span on two frames). 

A spare (`/`) is when the player knocks down all 10 pins in two rolls. In case of a spare, the player receives 10 points plus the points of the single next roll as a bonus. 

In the tenth frame a player who rolls a spare or a strike is allowed to roll respectively one and two extra rolls, with a new set of pins each time (these are part of the same frame). Each roll counts only for total number of pins knocked down.

Example:
```
01  02  03  04  05  06  07  08  09  10   (frames)
X   61  5/  7/  X   X   8/  81  X   XXX  (rolls)
17  24  41  61  89  109 127 136 166 196  (points)
```