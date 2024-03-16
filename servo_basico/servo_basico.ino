#include <Servo.h>

Servo servoPan;
Servo servoTilt;

const int pinServoPan = 6; 
const int pinServoTilt = 5;

int posPan = 90; // Posición inicial del servo Pan
int posTilt = 90; // Posición inicial del servo Tilt

void setup() {

  servoPan.attach(pinServoPan);
  servoTilt.attach(pinServoTilt);
  
  servoPan.write(posPan);
  servoTilt.write(posTilt);
  
  Serial.begin(9600);
}

void loop() {

  if (Serial.available() > 0) {
    
    char input = Serial.read();
    
    switch (input) {
      case 'w':
        if (posTilt > 0) {
          posTilt -= 2;
          servoTilt.write(posTilt);
        }
        break;
        
      case 's':
        if (posTilt < 180) {
          posTilt += 2;
          servoTilt.write(posTilt);
        }
        break;
        
      case 'a':
        if (posPan > 0) {
          posPan -= 2;
          servoPan.write(posPan);
        }
        break;
        
      case 'd':
        if (posPan < 180) {
          posPan += 2;
          servoPan.write(posPan);
        }
        break;
    }
  }
}
