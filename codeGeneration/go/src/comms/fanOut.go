package comms

func FanOut[T any](c <-chan T) (<-chan T, <-chan T) {
	c1 := make(chan T)
	c2 := make(chan T)
	go func() {
		defer close(c1)
		defer close(c2)
		for value := range c {
			c1 <- value
			c2 <- value
		}
	}()
	return c1, c2
}
