set terminal png font arial 14 size 1920,1080
set output "plot.png"
set ylabel "Num Comparacoes"
set xlabel "Tam Vetor"
set style line 1 lc rgb '#ADFF2F' pt 7
set style line 2 lc rgb 'red' pt 7
plot "./resultados_quicksort.txt" using 1:2 title 'Resultado de cada execucao' with points ls 1,"./resultado_esperado_arredondado_quicksort.txt" using 1:2 title '2NlnN' with lines linecolor rgb 'blue' lw 2,"./resultado_medio_quicksort.txt" using 1:2 title 'Resultado medio' with points ls 2,"./resultado_esperado_quicksort.txt" using 1:2 title '2NlnN1.846N' with lines linecolor rgb 'black' lw 1
