# Weirdness

This project demonstrates a weird Scala bug.

Oops, as it turns out, this is already filed as [SI-7139](https://issues.scala-lang.org/browse/SI-7139).

## What's here

The `foo` package object declares two type aliases:

```scala
package ceedubs

package object foo {
  type Id[A] = A
  type Foo[A] = FooT[Id, A]
}
```

`FooT[F[_], A]` simply wraps an `F[A]`:

```scala
package ceedubs
package foo

class FooT[F[_], A](fa: F[A])

object Foo {
  def apply[A](a: A): Foo[A] = new FooT[Id, A](a)
}
```

Nothing particularly exciting.

## To the REPL

It works in the REPL just like you would expect:

```scala
scala> import ceedubs.foo.Foo
import ceedubs.foo.Foo

scala> val f1: Foo[Int] = Foo(1)
f1: ceedubs.foo.Foo[Int] = ceedubs.foo.FooT@7df35060
```

**Until** you try to use it a second time (note that the only change is the name of the `val`):

```scala
scala> val f2: Foo[Int] = Foo(1)
<console>:8: error: ceedubs.foo.Foo.type does not take parameters
       val f2: Foo[Int] = Foo(1)
```

What's going on here?
