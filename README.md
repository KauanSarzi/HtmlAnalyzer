# 🌐 HTML Analyzer - Analisador de Profundidade de Estruturas HTML

![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Stack](https://img.shields.io/badge/Stack-4CAF50?style=for-the-badge&logo=databricks&logoColor=white)
![HTTP](https://img.shields.io/badge/HTTP-005571?style=for-the-badge&logo=http&logoColor=white)

Programa Java que analisa estruturas HTML remotas e identifica o texto contido no nível mais profundo da hierarquia de tags. Desenvolvido como solução para teste técnico de estágio em desenvolvimento, demonstrando competências em estruturas de dados, parsing de texto e validação de sintaxe.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Demonstração](#-demonstração)
- [Como Executar](#-como-executar)
- [Casos de Teste](#-casos-de-teste)
- [Arquitetura da Solução](#-arquitetura-da-solução)
- [Aprendizados e Desafios](#-aprendizados-e-desafios)
- [Autor](#-autor)

---

## 🎯 Sobre o Projeto

HTML Analyzer é uma aplicação CLI que processa documentos HTML e identifica o texto localizado no nível mais profundo da estrutura hierárquica, utilizando uma **Stack** para rastrear a hierarquia de tags.

### O Desafio

Dado um documento HTML estruturado, identificar programaticamente qual texto está no nível mais profundo:

```html
<html>
  <head>
    <title>Este é o título.</title>
  </head>
  <body>
    Este é o corpo.
  </body>
</html>
```

**Resultado:** `Este é o título.`  
**Motivo:** Está em 3 níveis (`html > head > title`) vs 2 níveis (`html > body`)

### Restrições Técnicas

- ✅ Apenas JDK 17 nativo (sem bibliotecas externas)
- ✅ Sem packages XML/HTML/DOM nativos
- ✅ Compilação: `javac HtmlAnalyzer.java`
- ✅ Execução: `java HtmlAnalyzer <URL>`
- ✅ Parser manual implementado do zero

---

## 🚀 Funcionalidades

### 1. Análise de Profundidade 📊

Identifica o texto no nível mais profundo usando Stack para controle de hierarquia.

**Algoritmo:**
```
Para cada linha:
  - Tag abertura → push na Stack
  - Tag fechamento → valida e pop da Stack
  - Texto → profundidade = tamanho da Stack
```

**Complexidade:** O(n) tempo, O(d) espaço (d = profundidade máxima)

### 2. Validação de HTML ✅

Detecta documentos mal-formados através de balanceamento de tags:

- 🔴 Tags não fechadas
- 🔴 Tags desbalanceadas
- 🔴 Ordem incorreta de fechamento

### 3. Acesso HTTP 🌐

Lê conteúdo HTML de URLs remotas com tratamento de erros e suporte UTF-8.

---

## 🎨 Demonstração

### Exemplo 1: HTML Bem-Formado

**HTML:**
```html
<html>
<head>
<title>Este é o título.</title>
</head>
<body>Este é o corpo.</body>
</html>
```

**Output:**
```
Este é o título.
```

**Análise de profundidade:**
- `Este é o título.` → 3 níveis ✅
- `Este é o corpo.` → 2 níveis

---

### Exemplo 2: HTML Mal-Formado

**HTML:**
```html
<html>
<body>
<div>
Texto sem fechamento
</html>
```

**Output:**
```
malformed HTML
```

---

### Exemplo 3: Erro de Conexão

**Comando:**
```bash
java HtmlAnalyzer http://url-invalida.com
```

**Output:**
```
URL connection error
```

---

## 🚀 Como Executar

### Pré-requisitos

- JDK 17 ou superior instalado

### Compilação

```bash
javac HtmlAnalyzer.java
```

### Execução

```bash
# Sintaxe
java HtmlAnalyzer <URL>

# Exemplo
java HtmlAnalyzer http://example.com/page.html
```

### Teste Local (Opcional)

```bash
# 1. Crie um arquivo HTML de teste
cat > test.html << 'EOF'
<html>
<head><title>Teste</title></head>
<body>Corpo</body>
</html>
EOF

# 2. Inicie servidor HTTP local
python3 -m http.server 8000

# 3. Execute o analyzer
java HtmlAnalyzer http://localhost:8000/test.html
```

---

## ✅ Casos de Teste

### Casos Válidos

| Caso | Output |
|------|--------|
| HTML com 3 níveis | Texto do nível mais profundo |
| Múltiplos textos mesma profundidade | Primeiro texto encontrado |
| Linhas em branco | Ignora corretamente |
| Espaços de indentação | Remove automaticamente |

### Casos de Erro

| Caso | Output |
|------|--------|
| Tag não fechada | `malformed HTML` |
| Tags desbalanceadas | `malformed HTML` |
| URL inválida | `URL connection error` |
| Sem argumentos | (sem output) |

---

## 🏗️ Arquitetura da Solução

### Estrutura do Código

```java
HtmlAnalyzer
├── main()              // Validação e tratamento de erros
├── processHtml()       // Lógica principal de parsing
├── isOpeningTag()      // Detecta tags de abertura
├── isClosingTag()      // Detecta tags de fechamento
└── extractTagName()    // Extrai nome da tag
```

### Algoritmo Principal

```java
Stack<String> tagStack = new Stack<>();
String deepestText = null;
int maxDepth = -1;

for (linha em HTML) {
    if (tag abertura) {
        tagStack.push(tagName);
    }
    else if (tag fechamento) {
        if (tagStack.isEmpty() || tagStack.peek() != tagName)
            → HTML mal-formado
        tagStack.pop();
    }
    else { // texto
        profundidade = tagStack.size();
        if (profundidade > maxDepth) {
            maxDepth = profundidade;
            deepestText = texto;
        }
    }
}

if (!tagStack.isEmpty())
    → HTML mal-formado
```

### Código Crítico

**Validação de fechamento:**
```java
if (tagStack.isEmpty() || !tagStack.peek().equals(tagName)) {
    structureError = true;
    break;
}
```

**Por quê?**
1. Stack vazia → tag fechada sem abertura
2. Topo diferente → desbalanceamento (violação LIFO)

---

## 🎓 Aprendizados e Desafios

### Competências Desenvolvidas

- ✅ **Estruturas de Dados**: Aplicação prática de Stack para balanceamento
- ✅ **Parsing**: Implementação de parser manual sem bibliotecas
- ✅ **Validação Sintática**: Detecção de estruturas hierárquicas mal-formadas
- ✅ **HTTP e Networking**: Requisições e tratamento de streams
- ✅ **Clean Code**: Métodos focados e nomenclatura descritiva

### Desafios Superados

| Desafio | Solução |
|---------|---------|
| Detectar HTML mal-formado | Validação dupla: durante parsing e ao final (Stack vazia) |
| UTF-8 em URLs | `StandardCharsets.UTF_8` no InputStreamReader |
| Primeiro texto na profundidade | Usar `>` em vez de `>=` na comparação |
| Tags sem validação de conteúdo | Parser simples com `<`, `>` e `/` |

### Insights Técnicos

**Stack como solução elegante:**
```java
// Profundidade = tamanho da Stack
int depth = tagStack.size();
```

O tamanho da pilha representa naturalmente quantas tags estão abertas, traduzindo hierarquia em um número.

---

## 📋 Requisitos Cumpridos

### Funcionais

✅ Identifica texto no nível mais profundo  
✅ Retorna primeiro texto em caso de empate  
✅ Ignora linhas em branco e indentação  
✅ **[BÔNUS]** Detecta HTML mal-formado  

### Técnicos

✅ Java JDK 17  
✅ Sem bibliotecas externas  
✅ Sem packages XML/HTML/DOM  
✅ Compilação: `javac HtmlAnalyzer.java`  
✅ Execução: `java HtmlAnalyzer <URL>`  
✅ Outputs padronizados  

---

## 🔮 Possíveis Melhorias

- [ ] Suporte a tags auto-fecháveis (`<br/>`, `<img/>`)
- [ ] Ignorar comentários HTML (`<!-- -->`)
- [ ] Suporte a atributos em tags
- [ ] Modo verboso com log de profundidades

---

## 👨‍💻 Autor

**Kauan Sarzi da Rocha**

🎓 **Contexto**
- Teste Técnico: Software Development Intern
- Solução implementada seguindo rigorosamente as especificações

💼 **Competências Demonstradas**
- Estruturas de Dados (Stack/Pilha)
- Parsing e Validação Sintática
- Programação Defensiva
- Clean Code e Boas Práticas

---

## 📫 Contato

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/kauan-sarzi)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/kauansarzi)
[![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:kauansarzi24@gmail.com)

---

<div align="center">

**⭐ Projeto desenvolvido para processo seletivo de estágio em desenvolvimento**

*Implementado com 💚 e Stack por [Kauan Sarzi](https://github.com/kauansarzi)*

</div>
