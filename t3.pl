% Exec 1
zeroInit(L) :-
L=[0|T]	.
% Exec 2
has5(L):-
	L=[_,_,_,_,_].
% Exec 3
hasN(L,N):-
	length(L, N).
%Exec 4
potN0(0,[1]).
potN0(N,L):-
	NR is N-1,
	L = [H|T],
	H is 2^N,
	potN0(NR, T). 	
% Exec 5
zipmult([],[],[]).
zipmult(L1,L2,L3) :-
    L1 = [A|B],
    L2 = [C|D],
    L3 = [E|F],
    E is A * C,
    zipmult(B,D,F).
% Exec 6
potencias(0,[]).
potencias(N,L):-
	potN0(N-1,A),
	reverse(A,L).
% Exec 7
positivos([],[]).
positivos([H|T],L2):-
	H>0,
	positivos(T,LR),
	L2=[H|LR].
positivos([_|T],L2):-
	positivos(T,LR),
	L2=LR.
% Exec 8
mesmaPosicao(A,[A|_],[A|_]).
mesmaPosicao(A,L1,L2):-
	L1=[_|T1],
	L2=[_|T2],
	mesmaPosicao(A,T1,T2).




