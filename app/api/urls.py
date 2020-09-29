from django.urls import path, include
from django.contrib import admin
from django.views.decorators.csrf import csrf_exempt

from . import views 
from .views import UserViewSet, UserProfileViewSet, OleaoViewSet, GarrafaViewSet
from django.views.generic.base import TemplateView
from rest_framework import routers
from django.contrib.auth import views as auth_views
from rest_framework_simplejwt.views import (
    TokenObtainPairView,
    TokenRefreshView,
    TokenVerifyView 
)

#Vistas do Rest Framework
router = routers.DefaultRouter()
router.register(r'users', views.UserViewSet),
router.register(r'usersprofile', views.UserProfileViewSet),
router.register(r'garrafa', views.GarrafaViewSet),
router.register(r'oleao', views.OleaoViewSet)


urlpatterns = [
    path('admin/', admin.site.urls),
    path(r'^$',views.index,name='index'),
    path(r'^special/',views.special,name='special'),
 
     #Login + Logout - Autentificação Básica -> Ver isto melhor!
    #path('api-auth/', include('rest_framework.urls')),
    #path(r'login/', csrf_exempt(views.user_login)),

    #Autentificação JWT - Web Token
    path('login', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    #path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path(r'logout/', auth_views.LogoutView.as_view(), name='logout'),
    
    #JsonResponse - Crud Applications
    path('addusers/', views.addusers, name = "Adiciona utilizadores"),
    path('addprofileusers/', views.addprofileusers, name ="Adiciona perfis do utilizador"),
    path('editusers/<int:id>', views.editusers, name = "Editar Users"),
    path('editprofileusers/<int:id>', views.editprofileusers, name = "Editar perfil do utilizador"),
    path('deleteusers/<int:id>', views.deleteusers, name = "Delete users"),
    path('deleteprofileusers/<int:id>', views.deleteprofileusers, name = "Delete perfil do utilizador"),
    
    #Rest-Api-Framework
    path('/', include(router.urls)),
    path('api/users/', views.user_list, name = "Users"),
    path('api/usersprofile/', views.userp_list, name = "User Profile"),
    path('api/garrafas/', views.garrafa_list, name = "Garrafa"),
    
    #Pesquisas
    path('api/users/searchuser/<str:username>/', views.userp_listname, name = "Pesquisa por nome dos users"),
    path('api/searchpoints/<int:id>', views.searchpoints, name = "Pesquisa dos pontos"), 
    path('api/searchpointsname/<str:username>', views.searchpointsname, name = "Pesquisa dos pontos pelo username"),
    path('api/usersprofile/searchprofuser/<str:username>/', views.userp_list_profile, name = "Pesquisa dos perfis do utilizador"),
    path('api/garrafas/searchbottleid/<int:userid>', views.searchbottleid, name = "Pesquisa da garrafa pelo userid"),
    path('api/garrafas/searchbottlename/<str:username>', views.searchbottlename, name = "Pesquisa da garrafa pelo nome"),
    
    #Autentificação JWT - Web Token
    path('login', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    #path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
]
