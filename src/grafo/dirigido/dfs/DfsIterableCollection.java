package grafo.dirigido.dfs;

import grafo.dirigido.Aresta;
import grafo.dirigido.Vertice;
import grafo.dirigido.abstrato.IterableCollection;
import grafo.dirigido.bfs.BfsIterator;

import java.util.List;

public class DfsIterableCollection<T> implements IterableCollection {
    private List<Vertice<T>> vertices;
    private List<Aresta<T>> arestas;
    public DfsIterableCollection(List<Vertice<T>> vertices, List<Aresta<T>>  arestas) {
        this.vertices = vertices;
        this.arestas = arestas;
    }

    @Override
    public DfsIterator<T> createIterator() {
        return new DfsIterator<>(this.vertices, this.arestas);
    }
}
