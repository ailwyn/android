
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
  for (int i = 1; i < 256; i++)
  {
    show_no(i);
  }
}

void show_no(int n)
{
  for (int i = 0; i < 8; i++)
  {
    if (n % 2 == 1){
      digitalWrite(leds[i], HIGH);   // turn the LED on (HIGH is the voltage level)
    }else
    {
      digitalWrite(leds[i], LOW);   // turn the LED on (HIGH is the voltage level)      
    }
    n = n >> 1;
  } 
  
  delay(500);               // wait for a second

  clear_all();
}

void clear_all()
{
  for (int i = 0; i < 8; i++)
  {
    digitalWrite(leds[i], LOW);    // turn the LED off by making the voltage LOW
  } 
}
