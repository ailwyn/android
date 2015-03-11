
int leds[] = {2,3,4,5,6,7,8,9};

// the setup routine runs once when you press reset:
void setup() {                
  // initialize the digital pin as an output.
  for (int i = 0; i < 8; i++)
  {
    pinMode(leds[i], OUTPUT);
  } 
       
}

// the loop routine runs over and over again forever:
void loop() {
  all_blink();
  clear_all();
  alt_blink();
  clear_all();
  race();
  clear_all();
}

void all_blink(){
  for (int i = 0; i < 8; i++)
  {
    digitalWrite(leds[i], HIGH);   // turn the LED on (HIGH is the voltage level)
  } 
  
  delay(500);               // wait for a second

  clear_all();
  
  delay(500);               // wait for a second
}

void alt_blink(){
  for (int i = 0; i < 8; i++)
  {
    if (i % 2 == 0){
      digitalWrite(leds[i], HIGH);   // turn the LED on (HIGH is the voltage level)
    }else{
      digitalWrite(leds[i], LOW);    // turn the LED off by making the voltage LOW
    } 
  }
  
  delay(500);               // wait for a second
  for (int i = 0; i < 8; i++)
  {
    if (i % 2 == 1){
      digitalWrite(leds[i], HIGH);   // turn the LED on (HIGH is the voltage level)
    }else{
      digitalWrite(leds[i], LOW);    // turn the LED off by making the voltage LOW
    } 
  }
  delay(500);               // wait for a second
}

void race()
{
  for (int i = 0; i < 8; i++)
  {
    digitalWrite(leds[i], HIGH);   // turn the LED on (HIGH is the voltage level)
    if (i > 0)
    {
      digitalWrite(leds[i-1], LOW);   // turn off the previous LED
    }
       delay(100);               // wait for a second 
  } 
  
  for (int i = 8; i > 0; i--)
  {
    digitalWrite(leds[i-1], HIGH);   // turn the LED on (HIGH is the voltage level)
    if (i < 8)
    {
      digitalWrite(leds[i], LOW);   // turn off the previous LED
    }
       delay(100);               // wait for a second 
  } 
  
}

void clear_all()
{
  for (int i = 0; i < 8; i++)
  {
    digitalWrite(leds[i], LOW);    // turn the LED off by making the voltage LOW
  } 
}
