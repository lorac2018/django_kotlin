# api/forms.py

from django import forms
from .models import UserProfile
from django.contrib.auth.forms import UserCreationForm
from .models import  UserProfile
from django.contrib.auth.models import User


#Create User

class UserForm(UserCreationForm):
    email = forms.EmailField()
    

    class Meta:
        model = User
        fields = ['username', 'first_name', 'email', 'last_name','password1', 'password2']

#Create UserProfiles
class UserProfileForm(forms.ModelForm):
   #username = forms.CharField(max_length=255) 
   
   class Meta:
        model = UserProfile
        fields = ['address']
