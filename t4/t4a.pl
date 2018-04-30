regra1(M):- M = [_,_,_,s,_,_,_]. % Música s ocupa a quarta posição
regra2(M):-		% W e Y tem que ser menor que a posição 3 da lista
	nth0(W,M,w),
	nth0(Y,M,y),
	W < 3,
	Y < 3.
regra3(M):-		% A posição de T tem que ser menor que a de W
	nth0(T,M,t),
	nth0(W,M,w),
	T < W.
regra4(M):-		% Um sucesso do rock tem que ocupar a sexta posição
	M = [_,_,_,_,_,SR,_],
	sucessodorock(SR).
regra5(M):-		% Um sucesso do rock tem que vir depois de uma música da banda
	musicadabanda(MB),
	sucessodorock(SR),
	nextto(MB,SR,M).
regra6(SR):-	% Um sucesso do rock
	sucessodorock(SR).
	
% Musica da banda pode ocupar qualquer espaço.
musicadabanda(_).
% Sucesso do rock é z
sucessodorock(z).

cd_musicas(M):- % junção
	regra1(M),
	regra2(M),
	regra3(M),
	regra4(M),
	regra5(M),
	nth0(5,M,SR), % A quinta posição vai ser SR, e passa para a regra 6 para teste.
	regra6(SR).