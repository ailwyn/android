/*
  Santa
  -----
  
  Operate the countdown timer stocking holder
  
 */
#include "Wire.h"
#define DS1307_I2C_ADDRESS 0x68

#define BIG 180
#define MED 80
#define SMALL 35

// define the pins for each of the digits
#define dig1 3
#define dig2 2

// define the pins attached to each segment
#define segA 9
#define segB 10
#define segC 6
#define segD 5
#define segE 8
#define segF 4
#define segG 7

// Pins for A B C D E F G, in sequence
const int segs[7] = { segA, segB, segC, segD, segE, segF, segG };

const byte sleeps[6] = { 0b0010010, 0b1000111, 0b0000110, 0b0000110, 0b0001100, 0b0010010 };

// Segments that make each number - in order 0bGFEDCBA due to bit-shift reading
const byte numbers[10] = { 0b1000000, 0b1111001, 0b0100100, 0b0110000, 0b0011001, 0b0010010,
0b0000010, 0b1111000, 0b0000000, 0b0010000 };


//================== LED ROUTINES ==========================//

void displayDigits(int num, int duration)
{
  int tens = num / 10;
  int units = num % 10;
  
  if(tens<10)
  {
    for(int i=0;i<duration;i++)
    {
      lightSegments(numbers[tens]);
      digitalWrite(dig1, HIGH);
      digitalWrite(dig2, LOW);
      delay (6);
      lightSegments(numbers[units]);
      digitalWrite(dig1, LOW);
      digitalWrite(dig2, HIGH);
      delay(6);
    }
  }
  clearDisplay();
}

void displayWord(const byte word1[], int charCount, int duration)
{

  int arraypos = 0;  

  while (arraypos <= charCount-1)
  {
    for(int i=0;i<duration;i++)
    {
      lightSegments(word1[arraypos]);
      digitalWrite(dig1, HIGH);
      digitalWrite(dig2, LOW);
      delay (6);
      lightSegments(word1[arraypos+1]);
      digitalWrite(dig1, LOW);
      digitalWrite(dig2, HIGH);
      delay(6);
    }
    clearDisplay();
    arraypos += 2;
  }

}

void lightSegments(byte number) {
  for (int i = 0; i < 7; i++) {
    int bit = bitRead(number, i);
    digitalWrite(segs[i], bit);
  }
}

void clearDisplay()
{
  for(int i=0;i<7;i++)
  {
    digitalWrite(segs[i], HIGH);    // set the LED off
  }
}

void chaseSegments()
{
    // Chase around 1st digit
  digitalWrite(dig1, HIGH); 
  digitalWrite(dig2, LOW);   
  for(int i=0;i<7;i++)
  {
    digitalWrite(segs[i], LOW);   // set the LED on
    delay(200);              // wait for a second
    digitalWrite(segs[i], HIGH);    // set the LED off
  }

  // Chase around 2nd digit  
  digitalWrite(dig1, LOW); 
  digitalWrite(dig2, HIGH); 
  for(int i=0;i<7;i++)
  {
    digitalWrite(segs[i], LOW);   // set the LED on
    delay(200);              // wait for a second
    digitalWrite(segs[i], HIGH);    // set the LED off
             
  }
  delay(500);
}

void celebrate()
{
  chaseSegments();
}

//================== RTC ROUTINES ==========================//

// Convert normal decimal numbers to binary coded decimal
byte decToBcd(byte val)
{
  return ( (val/10*16) + (val%10) );
}

// Convert binary coded decimal to normal decimal numbers
byte bcdToDec(byte val)
{
  return ( (val/16*10) + (val%16) );
}

// Gets the date and time from the ds1307
void getDateDs1307(byte *second,
          byte *minute,
          byte *hour,
          byte *dayOfWeek,
          byte *dayOfMonth,
          byte *month,
          byte *year)
{
  // Reset the register pointer
  Wire.beginTransmission(DS1307_I2C_ADDRESS);
  Wire.write( (byte) 0);
  Wire.endTransmission();
  
  Wire.requestFrom(DS1307_I2C_ADDRESS, 7);

  // A few of these need masks because certain bits are control bits
  *second     = bcdToDec(Wire.read() & 0x7f);
  *minute     = bcdToDec(Wire.read());
  *hour       = bcdToDec(Wire.read() & 0x3f);  // Need to change this if 12 hour am/pm
  *dayOfWeek  = bcdToDec(Wire.read());
  *dayOfMonth = bcdToDec(Wire.read());
  *month      = bcdToDec(Wire.read());
  *year       = bcdToDec(Wire.read());
}



//================== MAIN PROGRAM ==========================//


void setup() {   

  byte second, minute, hour, dayOfWeek, dayOfMonth, month, year;
  Wire.begin();
//  Serial.begin(9600);
  
  // Set all the Cathodes HIGH i.e. not to ground to turn all LEDs off
  for(int i=0;i<7;i++)
  {
    pinMode(segs[i], OUTPUT);
    digitalWrite(segs[i], HIGH); 
  } 
  
  // Setup the Common Anodes, set both to OFF i.e. 0v
  pinMode(dig1, OUTPUT);
  digitalWrite(dig1, LOW); 
  pinMode(dig2, OUTPUT);
  digitalWrite(dig2, LOW); 

//  chaseSegments();
}

void loop() {
//  Test for the 7 seg display
//  for(int i=0;i<21;i++)
//  {
//    displayDigits(i, MED);
//  }

  byte second, minute, hour, dayOfWeek, dayOfMonth, month, year;
  getDateDs1307(&second, &minute, &hour, &dayOfWeek, &dayOfMonth, &month, &year);

//  Serial.print(hour, DEC);
//  Serial.print(":");
//  Serial.print(minute, DEC);
//  Serial.print(":");
//  Serial.print(second, DEC);
//  Serial.print("  ");
//  Serial.print(month, DEC);
//  Serial.print("/");
//  Serial.print(dayOfMonth, DEC);
//  Serial.print("/");
//  Serial.print(year, DEC);
//  Serial.print("  Day_of_week:");
//  Serial.println(dayOfWeek, DEC);

  if (month == 12 && dayOfMonth < 25)
  {
    displayDigits( 25 - dayOfMonth, MED );
    delay(0);
    displayWord(sleeps, 6, SMALL);
  }
 
   delay(500); 


  if (month == 12 && dayOfMonth < 24)
  {
      displayDigits( 24 - dayOfMonth, MED );
      displayDigits( 23 - hour, MED );
      displayDigits( 59 - minute, MED );
      displayDigits( 59 - second, MED );
 
  } else if (month == 12 && dayOfMonth == 24)
    {
       int hoursLeft = 0;
       hoursLeft = (( 24 - dayOfMonth ) * 24) + (23 - hour);
       displayDigits( hoursLeft, MED );
       delay(500);
       displayDigits( 59 - minute, MED );
       delay(500);
       displayDigits( 59 - second, MED );
     } else if (month == 12 && dayOfMonth >= 25) 
       {
         celebrate();
       }
   
 
  delay(5000);
}
