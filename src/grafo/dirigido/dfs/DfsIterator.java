package grafo.dirigido.dfs;

import grafo.dirigido.Aresta;
import grafo.dirigido.Grafo;
import grafo.dirigido.VertexState;
import grafo.dirigido.Vertice;
import grafo.dirigido.abstrato.GrafoIterator;
import grafo.dirigido.abstrato.Iterator;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DfsIterator<T> extends GrafoIterator implements Iterator {

    public Stack<Vertice<T>> pilha;

    public DfsIterator(){
        super();
        this.pilha = new Stack<>();
    }

    public DfsIterator(List <Vertice<T>> vertices , List <Aresta<T>> arestas){
        super(vertices, arestas);
        this.pilha = new Stack<>();
    }


    public DfsIterator<T> DFS(){
        DfsIterator<T> Sii = new DfsIterator<T>();

        this.setAllVerticesUnvisited();

        List<Vertice<T>> p = this.getVertices();

        for( Vertice<T> u : p){
            runDFS( u, Sii );
        }

        return Sii;

    }

    //* Deep-First-Search (DFS): Busca em Profundidade */
    /**
     * Efetua um percurso em profundidade, baseado no algoritmo Deep-First-Search(DFS).
     * Este m�todo faz o percurso utilizando um vertice inicial, at� o vertice n que esteja
     * na rede do v�rtice inicial.
     * OBS: ESTE ALGORITMO � MATRIZ, o qual deve ser usado como c�digo inicial para adequar
     * �s necessidades de cada problema.
     *
     * @param *tag - String que identifica o vertice de partida.
     */
    public void DFS(T source){
        Vertice<T> u = null;


        if ((u = getVertice(source)) == null) {
            System.err.println("vertice nao encontrado em runDFS()");
            return;
        }

        this.setAllVerticesUnvisited();

        runDFS( u );

    }



    @SuppressWarnings("unchecked")
    private void runDFS(Vertice<T> u, DfsIterator<T> grafo){
        Vertice<T> w = null;
        List<Aresta<T>> uAdjacentes = null;

        u.setStatus(VertexState.Visited);
        grafo.addVertice( u.getCarga());

        uAdjacentes = u.getAdj();
        for(Aresta<T> arco: uAdjacentes){
            w = (Vertice<T>) arco.getDestino();

            if( w.getStatus() == VertexState.Unvisited ) {
                runDFS(w,grafo);
                grafo.addAresta(  arco.getOrigem(), arco.getDestino(), arco.getPeso());
            }
        }

        u.setStatus(VertexState.Finished);
    }


    /**
     * M�todo secund�rio, que efetua realmente o percurso no grafo a partir de um
     * vertice espec�fico
     * OBS: ESTE ALGORITMO � MATRIZ, o qual deve ser usado como c�digo inicial para adequar
     * �s necessidades de cada problema.
     * DEPENDENCIA: s� pode ser chamado a partir de DFS();
     * @param u - O vertice de busca.
     */

    private void runDFS(Vertice<T> u){
        Vertice<T> w = null;
        List<Aresta<T>> uAdjacentes = null;
        if(!this.hasNext())
            return;

        u.setStatus(VertexState.Visited);
        System.out.print("\t" + u.toString() + "\n");

        uAdjacentes = u.getAdj();
        this.pilha.push(u);
        for(int i=0; i < uAdjacentes.size(); i++ ){
            w = this.getNext();

            if( w.getStatus() == VertexState.Unvisited )
                runDFS(w);
        }
        this.posicaoAtual++;
        u.setStatus(VertexState.Finished);
    }

    @Override
    public boolean hasNext() {
        return this.posicaoAtual < this.vertices.size();
    }

    @Override
    public Vertice<T> getNext() {
        Vertice<T> w = null;
        List<Aresta<T>> uAdjacentes = null;

        Vertice<T> u = this.pilha.pop();
        u.setStatus(VertexState.Visited);
        System.out.print("\t" + u.toString() + "\n");

        uAdjacentes = u.getAdj();

        for(Aresta<T> arco: uAdjacentes){

            w = arco.getDestino();
            this.pilha.push(w);

            if( w.getStatus() == VertexState.Unvisited )
                this.getNext();
        }
        this.posicaoAtual++;
        return u;
    }

    @Override
    public void reset() {
        this.vertices.clear();
        this.arestas.clear();
        this.posicaoAtual = 0;
    }
}
