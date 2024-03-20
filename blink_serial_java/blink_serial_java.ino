void setup()
{
  Serial.begin(9600);
  pinMode(13, OUTPUT);
}
//--------------------------
void loop()
{
  byte inByte;
  if(Serial.available())
  {
    inByte = Serial.read();
    for(byte i=1; i<=inByte*2; i++)
    {
      digitalWrite(13, !digitalRead(13));
      delay(200);
    }
  }
}