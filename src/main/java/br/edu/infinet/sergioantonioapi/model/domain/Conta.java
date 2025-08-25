 package br.edu.infinet.sergioantonioapi.model.domain;

 import jakarta.persistence.*;

 @Entity
 public class Conta {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     private String nome;

     private String tipo; // CORRENTE, POUPANCA, CARTEIRA

     private Double saldoInicial;

     private boolean principal;

     @ManyToOne
     @JoinColumn(name = "usuario_id")
     private Usuario usuario;

     public Integer getId() {
         return id;
     }

     public void setId(Integer id) {
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

     public Double getSaldoInicial() {
         return saldoInicial;
     }

     public void setSaldoInicial(Double saldoInicial) {
         this.saldoInicial = saldoInicial;
     }

     public boolean isPrincipal() {
         return principal;
     }

     public void setPrincipal(boolean principal) {
         this.principal = principal;
     }

     public Usuario getUsuario() {
         return usuario;
     }

     public void setUsuario(Usuario usuario) {
         this.usuario = usuario;
     }

     @Override
     public String toString() {
         return super.toString() + String.format(" - %s - Tipo de Conta: %s - Saldo: %.2f - usuario: %s - Principal: %s ",
                 nome, tipo, saldoInicial, usuario != null? usuario.getNome() : null, principal ? "Sim" : "Não");
     }
 }