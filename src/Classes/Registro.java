/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author jean
 */
public class Registro {
    
    String nome;
    String tipo;
    int ano;
    String temporada;
    String generos;
    int epitotal;
    int epiatual;
    int nota;
    int id;
    String descricao;
    String modificacao;
    String drop;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }

    public int getEpitotal() {
        return epitotal;
    }

    public void setEpitotal(int epitotal) {
        this.epitotal = epitotal;
    }

    public int getEpiatual() {
        return epiatual;
    }

    public void setEpiatual(int epiatual) {
        this.epiatual = epiatual;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void setUltimaModificacao(String modificacao){
        this.modificacao = modificacao;
    }

    public String getUltimaModificacao() {
       return modificacao;
    }
    
    public void setDrop(String drop){
        this.drop = drop;
    }
    
    public String getDrop(){
        return drop;
    }
    
    
    
    
    
}
