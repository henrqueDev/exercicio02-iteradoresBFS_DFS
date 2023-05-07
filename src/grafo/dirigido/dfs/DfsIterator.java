package grafo.dirigido.dfs;

import grafo.dirigido.Aresta;
import grafo.dirigido.Grafo;
import grafo.dirigido.VertexState;
import grafo.dirigido.Vertice;
import grafo.dirigido.abstrato.GrafoIterator;

import java.util.List;

public class DfsIterator<T> extends GrafoIterator {

    public DfsIterator(){
        super();
    }

    public DfsIterator(List <Vertice<T>> vertices , List <Aresta<T>> arestas){
        super(vertices, arestas);
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
    @SuppressWarnings("unchecked")
    private void runDFS(Vertice<T> u){
        Vertice<T> w = null;
        List<Aresta<T>> uAdjacentes = null;

        u.setStatus(VertexState.Visited);
        System.out.print("\t" + u.toString() + "\n");

        uAdjacentes = u.getAdj();

        for(Aresta<T> arco: uAdjacentes){
            w = (Vertice<T>) arco.getDestino();
            if( w.getStatus() == VertexState.Unvisited )
                runDFS(w);
        }

        u.setStatus(VertexState.Finished);
    }

}
