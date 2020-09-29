from django.contrib import admin
from .models import UserProfile, Oleao, Garrafa


# Register your models here.

#O administrador tem acesso ao perfil do utilizador,(podendo adicionar os pontos) ao oleao (e o rfid) bem como à garrafa.
#O utilizador comum apenas tem acesso ao seu perfil (apenas podendo modificar os seus dados) e a identificação da garrafa
admin.site.register(UserProfile)
admin.site.register(Oleao)
admin.site.register(Garrafa)
