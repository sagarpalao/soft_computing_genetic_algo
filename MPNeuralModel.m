% Program to generate ANDNOT and XOR fucntion using McCulloch-Pitts neuron
% model by Matlab Program.

X1 = [ 0 0 1 1];
X2 = [ 0 1 0 1];

% Weight array
W = [ 2 -1 ];

% input variable
X = [X1 ; X2]'*W';

theta = 2;
Yin =  zeros(length(X),1);

for i = 1:4
   if X(i) >= theta
       Yin(i) = 1;
   else
       Yin(i) = 0;
   end
end

disp('McCulloch-Pitts: Logical ANDNOT :');
disp('     X1   X2    Output');
output = [X1',X2',Yin];
disp(output);
