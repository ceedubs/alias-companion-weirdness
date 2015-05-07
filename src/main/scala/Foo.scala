package ceedubs
package foo

class FooT[F[_], A](fa: F[A])

object Foo {
  def apply[A](a: A): Foo[A] = new FooT[Id, A](a)
}
