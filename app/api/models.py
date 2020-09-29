from django.db import models
from django.contrib.auth.models import User

class UserProfile(models.Model):
    
    user = models.OneToOneField(User, on_delete=models.CASCADE)  
    address = models.CharField(max_length=50)
    points = models.IntegerField(default=0)


class Oleao(models.Model):
    rfid = models.CharField(max_length=55)
    location = models.CharField(max_length=50)

    def __str__(self):
        return self.rfid

class Garrafa(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    oleao = models.ForeignKey(Oleao, on_delete=models.CASCADE)