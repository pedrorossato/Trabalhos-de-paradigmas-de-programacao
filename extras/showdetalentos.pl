%Show de Talentos
%No tradicional Show de Talentos da escola os alunos podem apresentar-se para mostrar suas diversas
%habilidades. Vale tudo: música, drama, malabarismo,... Este ano, sete alunos (A, B, C, D, E, F e G)
%estão inscritos. Cada aluno se apresentará uma única vez, em um dos sete turnos do Show, numerados
%de 1 a 7. As seguintes restrições devem ser obedecidas para decidir a ordem de apresentação:
%• A deve apresentar-se no turno 3 ou no turno 5.
%• F não pode se apresentar nem no turno 4 nem no turno 6.
%• Se D se apresentar no turno 1, C deve se apresentar no turno 2.
%• Se E se apresentar no turno 4, F deve se apresentar no turno 5.
%• B deve se apresentar no turno imediatamente após o turno em que C se apresentar.
regra1(ST):-
	nth0(A,ST,a),
	A=:=2,
	A=:=4.
regra2(ST):-
	nth0(F,ST,f),
	F=\=3,
	F=\=5.
regra3(ST):- 
	nth0(D,ST,d),
	D=:=0,
	nextto(d,c,ST).
regra4(ST):-
	nth0(E,ST,e),
	E=:=3,
	nextto(e,f,ST).
regra5(ST):-
	nextto(c,b,ST).

showdetalentos(ST):-
	regra1(ST),
	regra2(ST),
	regra3(ST),
	regra4(ST),
	regra5(ST).

%Questão 9. Qual das alternativas abaixo é uma
%ordem válida para as apresentações?
%(A) F,B,C,G,A,D,E
%(B) F,D,E,G,A,C,B
%(C) F,D,A,E,C,B,G
%(D) C,B,A,G,E,F,D
%(E) C,B,F,A,G,E,D 