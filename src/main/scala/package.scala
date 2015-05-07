package ceedubs

package object foo {
  type Id[A] = A
  type Foo[A] = FooT[Id, A]
}
