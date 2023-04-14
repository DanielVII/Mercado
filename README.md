<h1 align="center">Simulador de Mercado</h1>

### :bookmark_tabs: Pré-requesitos

### Para rodar essa aplicação é necessario as seguintes ferramentas :bookmark_tabs:

<ul>
    <li><a href="https://git-scm.com">Git</a></li>
    <li><a href="https://www.eclipse.org/downloads/">Eclipse</a></li>
    <li><a href="https://www.postgresql.org/download/">PostgreSQL</a> ou BD de sua preferência, mas com isso será necessario mudar info em alguns arquivos.</li>
</ul>

### Colocando para funcionar

#### 1- Clone o repositorio

```bash
$ git clone https://github.com/DanielVII/treino.git
```
#### 2- Criação de BD: Nome "Mercado", M maiusculo no começo mesmo.

#### 3- Configurações de conexão: Vá em Mercado\model\DAO\BaseDAO.java.

#### 4- Configuração do JDBC no eclipse.
<p><a href="https://youtu.be/svJAlmL2ABA?t=678">Video referência</a><p>
<ol>
    <li><a href="https://jdbc.postgresql.org/download/">Arquivo.jar</a></li>
    <li>Dentro do eclipse vá: (botão direito do mouse) em Mercado->Build Path->Configure Build Path...</li>
    <li>Em Libraries vá: "Add External JARs..."</li>
    <li>Procure o arquivo .jar baixado no passo 1 e click em abrir</li>
    <li>Click em "Aply and close"</li>
</ol>

#### 5- Configuração do javaFX no eclipse
<p><a href="https://www.youtube.com/watch?v=bC4XB6JAaoU&ab_channel=JavaCodingCommunity-ProgrammingTutorials">Video referência</a>. Esse video tem um erro, a forma certa está no passo 12.<p>
<ol>
    <li>Dentro do eclipse vá: help->Eclipse Marketplace...</li>
    <li>Procure: "fx". baixa o e(fx)clipse</li>
    <li>Baixe o <a href="https://gluonhq.com/products/javafx/">arquivo SDK</a> com as especificações de sua máquina</li>
    <li>Dentro do eclipse vá: window->Preferences</li>
    <li>Pesquise "user Libraries" e click nela</li>
    <li>Click em "New" e crie uma Librarie com o nome desejado</li>
    <li>Click na Librarie criada e vá em "Add External JARs..."</li>
    <li>Procure: Arquivo-SDK-Baixado/lib e abra</li>
    <li>Dentro de lib: selecione todos os .jar</li>
    <li>Add e Aply and close</li>
    <li>Dentro do eclipse vá: (botão direito do mouse) em Mercado->Build Path->Configure Build Path...</li>
    <li>Em Libraries->ModulePath vá: Add Library...->User Library->Library criada no passo 6</li>
    <li>Finish -> Aply</li>
    <li>Dentro do eclipse: (botão de run)setinha->Run Configurations...</li>
    <li>Arguments -> VM arguments</li>
    <li>Copie e coloque o seu caminho: --module-path "your-path\lib" --add-modules javafx.controls,javafx.fxml</li>
</ol>