package grafo.dirigido.abstrato;

import grafo.dirigido.Aresta;
import grafo.dirigido.VertexState;
import grafo.dirigido.Vertice;

import java.util.ArrayList;
import java.util.List;

public abstract class GrafoIterator<T> implements Iterator {

public static final int INFINITY = 99999;

/**
 * Colecao de vertices do grafo
 */
public List<Vertice<T>> vertices;
public List<Aresta<T>> arestas;

private int posicaoAtual;

public GrafoIterator(){
        this.vertices = new ArrayList<Vertice<T>>();
        this.arestas = new ArrayList<Aresta<T>>();
}
public GrafoIterator(List <Vertice<T>> vertices , List <Aresta<T>> arestas){
         this.vertices = vertices;
         this.arestas = arestas;
         posicaoAtual = 0;
}



        /**
 * Obtem um List de Vertices do grafo
 *
 * @return the vertices
 */
public List<Vertice<T>> getVertices() {
        return vertices;
        }


/**
 * Adiciona um vertice ao grafo.
 * Se o Vertice ja existir (pelo nome), o objeto nao � criado. Caso contrario, um
 * novo objeto do tipo Vertice � criado e retornado.
 *
 * @param *nome - Um String que representa o conteudo do vertice
 * @return O vertice criado
 */
public Vertice<T> addVertice(T carga) {
        Vertice<T> v;
        if ((v = (Vertice<T>) getVertice(carga)) == null) {
        v = new Vertice<>(carga);
        vertices.add(v);
        }
        return v;
        }

/**
 * M�todo que localiza e retorna a referencia para um vertice existente no grafo.
 *
 * @param *nome O conteudo para identificacao do vertice
 * @return A referencia para o vertice (quando existente) ou null.
 */
public Vertice<T> getVertice(T carga) {

        for (Vertice<T> u : vertices) {
        if (u.getCarga().equals(carga))
        return u;
        }
        return null;
        }

/**
 * Verifica a existencia de um vertice no grafo.
 *
 * @param v - O vertice desejado
 * @return <b>true</b>, caso o vertice perten�a ao grafo, ou <b>false</b> caso contrario.
 */


public boolean exists(Vertice<T> v) {

        for (Vertice<T> u : vertices) {
        if (u.getCarga().equals(v.getCarga()))
        return true;
        }
        return false;
        }

/**
 * Obtem o n�mero de vertices incidentes ao vertice u.
 *
 * @param u O vertice o qual se deseja obter a quantidade de incidentes
 * @return Um numero inteiro que quantifica o numero de vertices incidentes.
 */
public int getNumIncidentes(Vertice<T> u) {
        int count = 0;
        for (Aresta<T> arco : arestas) {
        if (arco.getDestino().equals(u))
        count++;
        }
        return count;
        }


@SuppressWarnings("unchecked")
public List<Vertice<T>> incidentes(Vertice<T> u) {
        List<Vertice<T>> vertex = new ArrayList<Vertice<T>>();
        for (Aresta<T> arco : arestas) {
        if (arco.getDestino().equals(u))
        vertex.add((Vertice<T>) arco.getOrigem());
        else if (arco.getOrigem().equals(u))
        vertex.add((Vertice<T>) arco.getDestino());

        }
        return vertex;
        }


/**
 * Adiciona uma aresta ao grafo.
 * O m�todo se encarreda de adcionar a aresta como ajacente ao vertice de origem.
 *
 * @param origem  - String que identifica o vertice de origem
 * @param destino - String que identifica o vertice de destino
 * @param peso    - O peso da aresta
 * @return Uma referencia para a aresta criada caso os vertices ja existam. Caso contrario,
 * retorna null.
 */
@SuppressWarnings("unchecked")
public Aresta<T> addAresta(T origem, T destino, int peso) {
        Vertice<T> u, v;
        Aresta<T> arco = null;
        if ((u = getVertice(origem)) == null || (v = getVertice(destino)) == null)
        return arco;

        return (Aresta<T>) addAresta(u, v, peso);
        }

/**
 * Adiciona uma aresta ao grafo.
 *
 * @param origem  - O objeto vertice de origem
 * @param destino - O objeto vertice de destino
 * @param peso    - o peso da aresta
 * @return Uma referencia para a aresta criada caso os vertices ja existam. Caso contrario,
 * retorna null.
 */
@SuppressWarnings("unchecked")
public Aresta<T> addAresta(Vertice<T> origem, Vertice<T> destino, int peso) {
        Aresta<T> e;
        /* Se a aresta j� existir, inclusive com o mesmo peso, n�o cria  */
        if ((e = (Aresta<T>) getAresta(origem, destino)) == null) {

        e = new Aresta<T>(origem, destino, peso);
        origem.addAdj(e);
        arestas.add(e);

        }
        return e;
        }

/**
 * Obtem a aresta espec�fica para um determinado par de vertices origem/destino.
 *
 * @param origem  - O v�rtice de origem
 * @param destino - O v�rtice de destino
 * @return Uma referencia para a aresta criada caso os vertices ja existam. Caso contrario,
 * retorna null
 */
public Aresta<T> getAresta(Vertice<T> origem, Vertice<T> destino) {
        Aresta<T> arc = null;
        for (int i = 0; i < arestas.size(); i++) {
        arc = arestas.get(i);
        if (arc.getOrigem().equals(origem) && arc.getDestino().equals(destino))
        return arc;
        }
        return null;
        }

/**
 * Obtem a colecao de arestas do grafo.
 *
 * @return As arestas.
 */
public List<Aresta<T>> getArestas() {
        return arestas;
        }


/**
 * Verifica se o grafo est� vazio.
 *
 * @return true - se o grafo est� vazio, false caso contr�rio.
 */
public boolean isEmpty() {
        return vertices.size() == 0;
        }

/**
 * Esvazia o grafo.
 */

public void reset() {
        vertices.clear();
        arestas.clear();
        }

public String toString() {
        String r = "";
        for (Vertice<T> u : vertices) {
        if (u.getAdj().size() != 0) {
        r += u.getCarga().toString() + " -> ";
        } else
        continue;

        for (Aresta<T> e : u.getAdj()) {
//            	r += u.tag + " -> ";
        Vertice<T> v = (Vertice<T>) e.getDestino();
        r += v.getCarga().toString() + ", ";
        }
        r += "\n";
        }
        return r;
        }

        public void checkDistanceIsInfinity(){
                for(int i=0; i < vertices.size(); i++ ){
                        if(this.vertices.get(i).getDistance() == INFINITY )
                                continue;
                        System.out.print("(v)=" + vertices.get(i).getCarga());
                        System.out.println(", dist(v)="  +  vertices.get(i).getDistance());
                }
        }

        public void setAllVerticesUnvisited(){
                for (int i = 0; i < this.vertices.size(); i++) {
                        this.vertices.get(i).setStatus(VertexState.Unvisited);
                        vertices.get(i).setDistance(INFINITY); // valor muito alto
                }
        }

        public void showMarked() {
                for (int i = 0; i < vertices.size(); i++) {
                        if (vertices.get(i).getStatus() == VertexState.Visited)
                        System.out.print(vertices.get(i).getCarga());
                }
        }

        @Override
        public boolean hasNext() {
                return this.posicaoAtual < this.vertices.size();
        }
        @Override
        public Vertice<T> getNext(){
                Vertice<T> obj = this.vertices.get(this.posicaoAtual);
                posicaoAtual++;
                return obj;
        };
}
