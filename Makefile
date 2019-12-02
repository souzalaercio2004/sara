
all: 
	gcc -o TesteQuickSort TesteQuickSort.c -lm
plot:
	gnuplot plot.plt
clean:
	rm -rf *.o *.txt TesteQuickSort