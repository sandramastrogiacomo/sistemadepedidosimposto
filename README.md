# Sistema de Pedidos com Cálculo de Impostos por UF

Este projeto foi desenvolvido como parte do bootcamp **Desenvolva+ Ada | Mercado Livre** e simula um sistema de processamento de pedidos que realiza o cálculo de impostos com base na Unidade Federativa (UF) do cliente. A aplicação foi construída utilizando **Java**, **Spring Boot** e segue uma arquitetura de microsserviços simplificada.

## 💡 Objetivo

O objetivo principal é exercitar conceitos de:

- Programação orientada a objetos com Java
- Arquitetura baseada em serviços
- Consumo de APIs internas
- Lógica de negócios aplicada ao cálculo de impostos
- Integração de microsserviços com Spring Boot

## 🧱 Estrutura do Projeto

O projeto é composto por dois serviços principais:

### 1. Serviço de Pedidos (`PedidoService`)
Responsável por:

- Criar novos pedidos
- Listar, atualizar e remover pedidos
- Enviar os dados para o serviço de impostos

### 2. Serviço de Impostos (`ImpostoService`)
Responsável por:

- Receber os dados do pedido
- Calcular o imposto com base na UF (estado) informada
- Retornar o valor total com imposto ao serviço de pedidos

## 🔧 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- REST APIs
- Lombok
- Insomnia/Postman (para testes das requisições HTTP)

## ▶️ Como Executar

1. Clone o repositório:

```bash
git clone https://github.com/sandramastrogiacomo/sistemadepedidosimposto.git

Importe os dois serviços no IntelliJ IDEA (ou outra IDE Java de sua preferência).

Certifique-se de que as portas configuradas para cada serviço não entrem em conflito (por exemplo: 8080 para PedidoService, 8081 para ImpostoService).

Execute ambos os serviços.

Utilize o Insomnia/Postman para testar os endpoints, começando pela criação de pedidos.

📬 Exemplos de Endpoints
Criar um novo pedido
POST /pedidos

{
  "cliente": "João Silva",
  "uf": "SP",
  "itens": [
    {
      "descricao": "Notebook",
      "quantidade": 1,
      "precoUnitario": 3500.00
    },
    {
      "descricao": "Mouse",
      "quantidade": 2,
      "precoUnitario": 50.00
    }
  ]
}

Calcular imposto
O imposto é calculado automaticamente após o envio do pedido, com base na UF fornecida.

📚 Aprendizados
Durante o desenvolvimento deste projeto, foram reforçados conhecimentos sobre:

Organização de projetos Spring Boot em múltiplos serviços

Comunicação entre APIs REST

Implementação de lógica de negócio orientada a regras fiscais regionais

Testes com ferramentas como Insomnia

🧑‍💻 Autora
Desenvolvido por Sandra Mastrogiacomo
Bootcamp: Desenvolva+ Ada | Mercado Livre
