# 📦 Controle de Estoque

Sistema web de controle de estoque desenvolvido com Java (Jakarta EE), JSP e banco de dados relacional. Permite o gerenciamento de empresas, produtos e movimentações de entrada e saída de estoque.

---

## 📋 Descrição

O sistema oferece as seguintes funcionalidades:

- **Empresas:** cadastro, edição, exclusão e busca de empresas (nome, CNPJ, telefone)
- **Produtos:** cadastro, edição, exclusão e busca de produtos vinculados a uma empresa (nome, preço, quantidade em estoque)
- **Movimentações:** registro de entradas e saídas de estoque com controle de saldo, data e observações
- **Validação de estoque:** impede saídas quando a quantidade solicitada é maior que o saldo disponível
- **Alertas visuais:** exibição de erros diretamente na interface

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Descrição |
|---|---|
| Java (Jakarta EE) | Lógica de negócio e Servlets |
| JSP + JSTL | Interface web dinâmica |
| HTML5 + CSS3 | Estrutura e estilo das páginas |
| Google Fonts (Roboto) | Tipografia |
| Apache Tomcat | Servidor de aplicação |
| Banco de dados relacional | Persistência dos dados (via DAO) |

---

## 🚀 Como Instalar e Rodar

### Pré-requisitos

- [Java JDK 11+](https://www.oracle.com/java/technologies/downloads/)
- [Apache Tomcat 10+](https://tomcat.apache.org/)
- [NetBeans IDE](https://netbeans.apache.org/) (recomendado) ou IntelliJ/Eclipse
- Banco de dados (MySQL ou outro compatível)

### Passo a Passo

**1. Clone o repositório**
```bash
git clone https://github.com/seu-usuario/controle-de-estoque.git
```

**2. Configure o banco de dados**

- Crie o banco de dados no seu SGBD
- Ajuste as credenciais de conexão na classe DAO correspondente (geralmente em `model/dao/`)

**3. Importe o projeto**

- Abra o NetBeans
- Vá em `File > Open Project` e selecione a pasta do projeto

**4. Configure o servidor**

- Adicione o Apache Tomcat nas configurações do projeto
- Certifique-se de que o Tomcat está apontando para a versão correta do Jakarta EE

**5. Execute o projeto**

- Clique em `Run Project` (F6) no NetBeans
- O sistema será aberto automaticamente no navegador em:
```
http://localhost:8080/controle-de-estoque/
```

---

## 📁 Estrutura do Projeto
 
```
ControleDeEstoque-1.0/
├── Web Pages/
│   ├── META-INF/
│   ├── WEB-INF/
│   ├── images/                         # Imagens e fundo do sistema
│   ├── index.html                      # Página inicial
│   ├── empresa.jsp                     # Tela de empresas
│   ├── produto.jsp                     # Tela de produtos
│   └── movimentacao.jsp                # Tela de movimentações
│
├── Source Packages/
│   ├── controller/
│   │   ├── EmpresaSrv.java             # Servlet de empresas
│   │   ├── MovimentacaoSrv.java        # Servlet de movimentações
│   │   └── ProdutoSrv.java             # Servlet de produtos
│   │
│   ├── model/
│   │   ├── Empresa.java                # Entidade Empresa
│   │   ├── Movimentacao.java           # Entidade Movimentacao
│   │   ├── Produto.java                # Entidade Produto
│   │   └── TipoMovimentacao.java       # Enum ENTRADA / SAIDA
│   │
│   └── model.dao/
│       ├── ConnFactory.java            # Conexão com o banco
│       ├── DaoFactory.java             # Fábrica de DAOs
│       ├── EmpresaDao.java             # CRUD de empresas
│       ├── InterfaceDao.java           # Interface genérica DAO
│       ├── MovimentacaoDao.java        # CRUD de movimentações
│       └── ProdutoDao.java             # CRUD de produtos
│
└── src/main/resources/
    └── META-INF/
        └── persistence.xml             # Configuração JPA
```

---

## 📄 Licença

Projeto desenvolvido para fins educacionais — 2026.
