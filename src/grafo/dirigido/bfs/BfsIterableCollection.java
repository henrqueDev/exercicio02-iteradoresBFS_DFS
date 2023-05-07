package grafo.dirigido.bfs;

import grafo.dirigido.Aresta;
import grafo.dirigido.Vertice;
import grafo.dirigido.abstrato.IterableCollection;

import java.util.List;

public class BfsIterableCollection<T> implements IterableCollection {
    private List<Vertice<T>> vertices;
    private List<Aresta<T>> arestas;
    public BfsIterableCollection(List<Vertice<T>> vertices, List<Aresta<T>>  arestas) {
        this.vertices = vertices;
        this.arestas = arestas;
    }

    @Override
    public BfsIterator<T> createIterator() {
        return new BfsIterator<>(this.vertices, this.arestas);
    }
}
