% Program to generate XOR fucntion using McCulloch-Pitts neuron
% model by Matlab Program.
clear;
clc;
X1 = [ 0 0 1 1];
X2 = [ 0 1 0 1];

% Weight array
W = [ 2 -1 ];

% input variable
Y1 = [X1 ; X2]'*W';
Y2 = [X2 ; X1]'*W';

Z1 = zeros(length(Y1),1);
Z2 = zeros(length(Y2),1);
theta = 2;
for i = 1:4
   if Y1(i) >= theta
      Z1(i) = 1;
   else
      Z1(i) = 0;
   end
end

for i = 1:4
   if Y2(i) >= theta
      Z2(i) = 1;
   else
      Z2(i) = 0;
   end
end

W2 = [ 2 2 ];
Z =  [Z1, Z2]*W2';

Zin = zeros(length(Z),1);
theta = 1;
for i = 1:4
   if Z(i) >= theta
       Zin(i) = 1;
   else
       Zin(i) = 0;
   end
end

disp('McCulloch-Pitts: Logical XOR :');
disp('     X1   X2    Output');
output = [X1',X2',Zin];
disp(output);
