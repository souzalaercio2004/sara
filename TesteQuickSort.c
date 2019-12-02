#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

#define TAM_INICIAL 1000
#define INCREMENTO 1000
#define NUM_INCREMENTOS 99
#define TESTES_POR_TAMANHO 100
#define MAX_RANDOM 100000
int numIteracoes = 0;

/*Gera um vetor com n numeros aleatorios*/
int* gerarVetorAleatorio(int n){
    int* vetor = (int*) malloc(n*sizeof(int));
    register int i;
    for (i=0; i<n; i++){
        vetor[i] = rand() % MAX_RANDOM;
    }
    return vetor;
}

void troca (int* A, int i, int j){
    int Aux;
    Aux= A[i];
    A[i]= A[j];
    A[j]= Aux;
}


int particionar(int* A, int p, int r){
    int pivo = A[r];
    register int i = p-1;
    register int j;
    for (j=p; j<r; j++){
        if (A[j] <= pivo){
            i++;
            troca(A, i, j);
        }
    }
    troca(A, i+1, r);
    numIteracoes += r-p+2;
    return (i+1);
}

void quickSort(int* A, int p, int r){
    int q;
    if (p<r){
        q = particionar(A, p, r);
        quickSort(A, p, q-1);
        quickSort(A, q+1, r);
    }
}


int main (void){

    double semente = time(NULL);
    srand(semente);

    // Cria matriz para armazenar os resultados dos testes
    int tamLog = NUM_INCREMENTOS + 1;
    int** logMatrix = (int**) malloc (tamLog * sizeof(int*));
    for (int i = 0; i < tamLog; i++) {
        logMatrix[i] = (int*) malloc (TESTES_POR_TAMANHO * sizeof(int));
    }

    int tamVetor;
    int* vetor;
    for (int i = 0; i <= NUM_INCREMENTOS; i++) {
            tamVetor = TAM_INICIAL + i*INCREMENTO;
            printf("Executando testes com tamanho %d\n", tamVetor);

            for (int j = 0; j < TESTES_POR_TAMANHO; j++) {
                vetor = gerarVetorAleatorio(tamVetor);

                numIteracoes = 0;
                quickSort(vetor,0,tamVetor-1);
                logMatrix[i][j] = numIteracoes;

                free(vetor);
            }
    }

    // Gerar saida em arquivo para posterior criacao do grafico
    printf("salvando em: resultados_quicksort.txt\n");
	FILE* arquivo = fopen("resultados_quicksort.txt", "w");
	if (arquivo == NULL) {
		printf("Nao foi possivel criar arquivo de saida.\n");
		exit(0);
	}

    for (int i = 0; i < tamLog; i++) {
        for (int j = 0; j < TESTES_POR_TAMANHO; j++) {
            fprintf(arquivo, "%d\t%d\n", i*INCREMENTO + TAM_INICIAL, logMatrix[i][j]);
        }
    }
    fclose(arquivo);

    // Gerar saida em arquivo com valores medios
    printf("salvando em: resultado_medio_quicksort.txt\n");
	arquivo = fopen("resultado_medio_quicksort.txt", "w");
	if (arquivo == NULL) {
		printf("Nao foi possivel criar arquivo de saida.\n");
		exit(0);
	}

    double soma;
    for (int i = 0; i < tamLog; i++) {
        soma = 0.0;
        for (int j = 0; j < TESTES_POR_TAMANHO; j++) {
            soma += logMatrix[i][j];
        }
        fprintf(arquivo, "%d\t%f\n", i*INCREMENTO + TAM_INICIAL, soma/100);
    }
    fclose(arquivo);

    // Gerar saida em arquivo com valores da funcao 2*N*log(N)-1.846
    printf("salvando em: resultado_esperado_quicksort.txt\n");
	arquivo = fopen("resultado_esperado_quicksort.txt", "w");
	if (arquivo == NULL) {
		printf("Nao foi possivel criar arquivo de saida.\n");
		exit(0);
	}

    for (int i = 0; i < tamLog; i++) {
        int N = i*INCREMENTO + TAM_INICIAL;
        fprintf(arquivo, "%d\t%f\n", N, 2*N*log(N)-1.846*N);
    }
    fclose(arquivo);

	// Gerar saida em arquivo com valores da funcao 2*N*log(N)
    printf("salvando em: resultado_esperado_arredondado_quicksort.txt\n");
	arquivo = fopen("resultado_esperado_arredondado_quicksort.txt", "w");
	if (arquivo == NULL) {
		printf("Nao foi possivel criar arquivo de saida.\n");
		exit(0);
	}

    for (int i = 0; i < tamLog; i++) {
        int N = i*INCREMENTO + TAM_INICIAL;
        fprintf(arquivo, "%d\t%f\n", N, 2*N*log(N));
    }
    fclose(arquivo);

    /*
    Para montar o grafico digite no terminal:

    gnuplot
	set terminal png font arial 14 size 1920,1080
	set output "plot.png"
    set ylabel "Num Comparacoes"
    set xlabel "Tam Vetor"
	set style line 1 lc rgb '#ADFF2F' pt 7
	set style line 2 lc rgb 'red' pt 7
	plot "./resultados_quicksort.txt" using 1:2 title 'Resultado de cada execucao' with points ls 1,"./resultado_esperado_arredondado_quicksort.txt" using 1:2 title '2NlnN' with lines linecolor rgb 'blue' lw 2,"./resultado_medio_quicksort.txt" using 1:2 title 'Resultado medio' with points ls 2,"./resultado_esperado_quicksort.txt" using 1:2 title '2NlnN1.846N' with lines linecolor rgb 'black' lw 1

    Obs: set xrange [0:100000] tambem pode ser util.

    */

    /*for (int i = 0; i < tamLog; i++) {
        free(logMatrix[i]);
    }
    free(logMatrix);*/

    return 0;
}
