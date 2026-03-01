/*
Si定义为序列的前i项和；a[n]从0开始；
则有：
S1 = S0 + a0
S2 = S1 + a1
S3 = S2 + a2
......
Si = Si-1 + ai-1
（Si = Si-2 + ai-2 +ai-1）
......

移项：
ai = Si+1 - Si，或ai-1 = Si - Si-1

求和规律：
ai                       = Si+1 - Si
ai + ai-1            = Si+1 - Si-1
ai + ai-1 + ai-2 = Si+1 - Si-2
......

*/