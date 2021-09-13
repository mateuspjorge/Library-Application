## COMO TRABALHAR NESTE REPOSITORIO

Este documento consiste em como se deve trabalhar no repositório sem a geração de conflitos ou sobreposição de código

*Recomendado a leitura completa deste documento*

---

## IDE RECOMENDADA

Nós recomendamos a IDE [Spring Tools (STS)](https://spring.io/tools3/sts/all)

Com esta IDE você terá acesso rápido a criação de novos projetos spring, além de ser uma IDE baseada no Eclipse, o que facilitará sua integração. Recomendamos a utilização do STS, contudo se sentir-se confortável com outra IDE, esta pode ser utilizada, já que todo o código é configurado via Maven. 

---

## Clone do repository

Nesta seção iniciaremos o processo de clone do repositório, que consiste em copiar todo o conteúdo de nossa branch para uma branch local (sincronizada com o repositório remoto) para que o dev possa iniciar a configuração do sistema.

Recomendamos a utilização de cliente GIT como o [Git Bash](https://git-scm.com/downloads), ou outro de sua preferência. 

Entre no repositório Github do projeto e siga os passos abaixo para clonar o repositório:

1. Em **Code** você verá o ícone de uma **prancheta**. Clique neste botão.
2. Copie o código que será mostrado no modal.
3. Abra o Git Bash ou outro cliente git navega até uma pasta onde você colocará o código (recomendamos uma pasta diferente da pasta do workspace do sts)
4. Execute o comando.
5. O clone do projeto será realizado pelo cliente.

---

## Importando projeto para o STS

Como mencionamos a IDE que recomendado é o STS, logo este tutorial assume que esta IDE foi a escolhida pelo dev.

1. Com o STS aberto click com o botão direito na área de projeto e selecione a opção *import* 
2. Na tela de seleção procure por **Existing Maven Projects**
3. Na tela de **import**, procure a pasta onde você clonou o repositório e selecione a pasta. É importante selecionar a pasta que contém todos os projetos, pois assim o STS se encarrega de importar todos os projetos maven que estão dentro da pasta.
4. Import os projetos.

Após o passo 4 o STS deve baixar todas as dependências automaticamente, se isto não acontecer Siga os passos a seguir para solucionar o problema:

1. Selecione os projetos que estão com problema
2. Click no botão direito e procure as opções **Maven -> Update Project**
3. Marque a opção **Force update**

Se mesmo assim não funcionar o problema busque ajuda com o time, estamos aqui para isso =)