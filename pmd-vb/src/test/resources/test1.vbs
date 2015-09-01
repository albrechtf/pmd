Private Sub cmd_compute_Click()
Dim myx, n, num As Integer
Dim r As Single
x = Txt_FirstNum.Text
r = Txt_CR
num = Txt_Terms.Text
List1.AddItem "n" & vbTab & "x"
List1.AddItem "___________"

If ((1 + 1) = 2) Then
  n = 1
End If
MyA$="Hallo"
n = 1
Do
x = x * r
List1.AddItem n & vbTab & x
n = n + 1
Loop Until n = num + 1
End Sub

