#include <Servo.h>

Servo servoPan;
Servo servoTilt;

const int pinServoPan = 10; 
const int pinServoTilt = 11;
const int laser = 13; 

int posPan = 90; // Posición inicial del servo Pan
int posTilt = 90; // Posición inicial del servo Tilt

void setup() {

  pinMode(laser,OUTPUT);

  servoPan.attach(pinServoPan);
  servoTilt.attach(pinServoTilt);
  
  servoPan.write(posPan);
  servoTilt.write(posTilt);
  
  Serial.begin(9600);
  
}

void loop() {

  digitalWrite(laser,LOW);

  if (Serial.available() > 0) {
    
    byte input = Serial.read();
    
    switch (input) {

      case 1:
        if (posTilt > 0) {
          posTilt -= 1;
          servoTilt.write(posTilt);
        }
        break;
        
      case 2:
        if (posTilt < 180) {
          posTilt += 1;
          servoTilt.write(posTilt);
        }
        break;
        
      case 4:
        if (posPan > 0) {
          posPan -= 1;
          servoPan.write(posPan);
        }
        break;
        
      case 3 :
        if (posPan < 180) {
          posPan += 1;
          servoPan.write(posPan);
        }
        break;

        case 5:
        servoPan.write(posPan);
        servoTilt.write(posTilt);
        break;

        case 6:
        digitalWrite(laser,HIGH);
        delay(5);
        break;
    }
  }
}
