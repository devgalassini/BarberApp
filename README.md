<div align="center">
    <h1>Barber App</h1>
    <a href="https://github.com/devgalassini/BarberApp/blob/master/barbershop.png">
        <img src="https://github.com/devgalassini/BarberApp/blob/master/barbershop.png" alt="Barber App" width="300"/>
    </a>
</div>

## Funcionalidades do App

- **Login do usuário**: baseado no nome do usuário e senha.
- **Lista de serviços**: exibida em formato de grade.
- **Campo de pesquisa**: para buscar serviços específicos.
- **Componente de Data**: seleção da data do agendamento com DatePicker.
- **Componente de Hora**: seleção da hora do agendamento com TimePicker.
- **Banco de dados online**: Firebase Firestore para armazenar e recuperar dados.
- **Botão de seleção**: CheckBox para opções adicionais.

## Linguagem de Programação e Ferramentas

- **Linguagem de Programação**: Kotlin
- **Back-end utilizado na aplicação**: Firebase
- **IDE**: Android Studio

## Design da Interface do Usuário (UI Design)

### Tela de Login
- **Campos de Texto**: Nome de Usuário e Senha
- **Botão**: Login
- **Link**: Esqueci a senha (opcional)

### Tela Principal
- **Barra de Navegação**: Menu lateral ou barra inferior com opções como "Serviços", "Agendamentos", "Perfil"
- **Lista de Serviços**: Exibida em formato de grade
- **Campo de Pesquisa**: Para buscar serviços específicos
- **Botão Flutuante**: Para agendar um novo serviço

### Tela de Agendamento
- **Lista de Serviços**: Seleção de serviço a ser agendado
- **DatePicker**: Seleção da data do agendamento
- **TimePicker**: Seleção da hora do agendamento
- **Checkbox**: Opções adicionais (ex.: serviço extra, notificações)
- **Botão**: Confirmar agendamento

## Implementação das Funcionalidades

### Login do Usuário
Autenticação baseada em nome de usuário e senha utilizando Firebase Authentication.

```kotlin
FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
    .addOnCompleteListener { task ->
        if (task.isSuccessful) {
            // Login successful
        } else {
            // Login failed
        }
    }
