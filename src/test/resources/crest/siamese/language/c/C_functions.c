#include <stdio.h>
#define PI 3.1416 // constant (global)

// table of contnent
int calculateMultiplication(int value_1, int value_2); // prototype

int global_variable=7; 
//global varibale declared here outside the function scope

int main()
{
   // execution point
   int temp = 5;// local variable declared within the function
   
   // 3,4 treated as arguments
   printf(" Printing global_variable from Main: %d \n", global_variable);
   global_variable=10;
   printf("%f", PI);
   int final_result = calculateMultiplication(3, 4); // function calling
   calculate(4,5);
   return 0;


   //temp,  final_result are destroyed from memory
}// global_variable is desctroyed from memory

int calculateMultiplication(int value_1, int value_2)
{
   printf(" Printing from Calculate : %d \n", global_variable);
   printf("%f", PI);
   // scope
   return value_1 * value_2*global_variable;
   // value_1 and value_2 destroyed from memory
}


///  global_variable can be accessed
