def fib1( n : Int) : Int = n match {
   case 0 => 0
   case 1 => 1
   case _ => fib1( n-1 ) + fib1( n-2 )
}

fib(50)
