from django.shortcuts import render, redirect
from django.http import HttpResponse, JsonResponse, HttpResponseBadRequest
from django.views.decorators.csrf import csrf_exempt
from django.views.decorators.csrf import csrf_protect
from django.contrib import messages
from . import serializers 
from . import models
from .models import UserProfile, User, Garrafa, Oleao
from .forms import UserProfileForm, UserForm
from .serializers import UserSerializer, UserProfileSerializer, GarrafaSerializer, OleaoSerializer, PointsSerializer
from rest_framework import viewsets, permissions
from rest_framework.parsers import JSONParser
from django_filters.rest_framework import DjangoFilterBackend
from django_filters import rest_framework as filters
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.decorators import login_required
from django.http import HttpResponseRedirect, HttpResponse
from django.urls import reverse
from django.views.generic import View
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from rest_framework.authtoken.models import Token


def index(request):
    return render(request,'index.html')
@login_required
def special(request):
    return HttpResponse("You are logged in !")
@login_required
def user_logout(request):
    logout(request)
    return HttpResponseRedirect(reverse('index'))


#Login
#@csrf_exempt
#def user_login(request):
 #   if request.method == 'POST':
  #      username = request.POST.get('username')
   #     password = request.POST.get('password')
    #    user = authenticate(username=username, password=password)
     #   if user:
      #      if user.is_active:
       #         login(request,user)
        #        return HttpResponseRedirect(reverse('index'))

         #   else:
               # return HttpResponse("Your account was inactive.")
        #else:
         #   print("Someone tried to login and failed.")
         #   print("They used username: {} and password: {}".format(username,password))
         #   return HttpResponse("Invalid login details given")
    #else:
     #   return render(request, 'login.html', {})

#Registo dos utilizadores
#def register(request):
#    if request.method == "POST":
#        u_form = UserForm(request.POST)
#        p_form = UserProfileForm(request.POST)
#        if u_form.is_valid() and p_form.is_valid():
#            user = u_form.save()
#            p_form = p_form.save(commit=False)
#            p_form.user = user
#            p_form.save()
#            messages.success(request, f'Registration complete! You may log in!')
#            return redirect('http://localhost:8000/api-auth/login/')
#    else:
#        u_form = UserForm(request.POST)
#        p_form = UserProfileForm(request.POST)
#    return render(request, 'register.html', {'form': u_form, 'p_form': p_form})


#Adicionar users
@csrf_exempt
def addusers(request):
   if request.method == 'POST':
        data = JSONParser().parse(request)
        serializer = UserSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status=201)
        return JsonResponse(serializer.errors, status=400)

#Adicionar usersprofile -- como associar ao user
@csrf_exempt
def addprofileusers(request):
   if request.method == 'POST':
        data = JSONParser().parse(request)
        serializer = UserProfileSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status=201)
        return JsonResponse(serializer.errors, status=400)

#Editar users
@csrf_exempt
def editusers(request, id):
    try:
        users = User.objects.get(id=id)
    except User.DoesNotExist:
        return HttpResponse(status=404)

    if request.method == 'PUT':
        data = JSONParser().parse(request)
        serializer = UserSerializer(snippet, data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data)
        return JsonResponse(serializer.errors, status=400)

#Editar usersprofile
@csrf_exempt
def editprofileusers(request, id):
    try:
        users = UserProfile.objects.get(id=id)
    except UserProfile.DoesNotExist:
        return HttpResponse(status=404)

    if request.method == 'PUT':
        data = JSONParser().parse(request)
        serializer = UserProfileSerializer(snippet, data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data)
        return JsonResponse(serializer.errors, status=400)

#Eliminar users
@csrf_exempt
def deleteusers(request, id):
    try:
        users = User.objects.get(id=id)
    except User.DoesNotExist:
        return HttpResponse(status=404)

    if request.method == 'DELETE':
        users.delete()
        return HttpResponse(status=204)

#Elimina usersprofile
@csrf_exempt
def deleteprofileusers(request, id):
    try:
        users = UserProfile.objects.get(id=id)
    except UserProfile.DoesNotExist:
        return HttpResponse(status=404)

    if request.method == 'DELETE':
        users.delete()
        return HttpResponse(status=204)




#Listagem dos users associadas:
@csrf_exempt
def user_list(request):
    """
     List all users, or create a new user.
    """
    if request.method == 'GET':
        user = User.objects.all()
        serializer = UserSerializer(user, many=True)
        return JsonResponse(serializer.data, safe=False)

#Listagem dos perfis dos utilizadores associadas:
@csrf_exempt
def userp_list(request):
    # List all users, or create a new user.
    if request.method == 'GET':
        userpr = UserProfile.objects.all()
        serializer = UserProfileSerializer(userpr, many=True)
        return JsonResponse(serializer.data, safe=False)

#Listagem da garrafa dos utilizadores
@csrf_exempt
def garrafa_list(request):
    """
     List all users, or create a new user.
    """
    if request.method == 'GET':
        user = Garrafa.objects.all()
        serializer = GarrafaSerializer(user, many=True)
        return JsonResponse(serializer.data, safe=False)

#Pesquisa dos utilizadores pelo username
def userp_listname(request, username):
    # List all users, or create a new user.
    if request.method == 'GET':
        userpr = User.objects.filter(username = username)
        serializer = UserSerializer(userpr, many=True)
        return JsonResponse(serializer.data, safe=False)

#Pesquisa dos perfis do utilizador pelo id do user
def userp_list_profile (request, username):

    if request.method == 'GET':
        userprofile = UserProfile.objects.filter(user__username = username)
        serializer = UserProfileSerializer(userprofile, many=True)
        return JsonResponse(serializer.data, safe=False)

#Pesquisa da garrafa pelo userid do utilizador
def searchbottleid(request, userid):

    if request.method == 'GET':
        userfetched = Garrafa.objects.filter(user__id = userid)
        serializer = GarrafaSerializer(userfetched, many = True)
        return JsonResponse(serializer.data, safe=False)

#Pesquisa da garrafa pelo nome do utilizador
def searchbottlename(request, username):
    
    if request.method == 'GET':
        userfetched = Garrafa.objects.filter(user__username = username)
        serializer = GarrafaSerializer(userfetched, many=True)
        return JsonResponse(serializer.data, safe=False)

#Pesquisa dos pontos do utilizador
def searchpoints(request, id):
    if request.method == 'GET':
        user = UserProfile.objects.filter(user__id = id)
        serializer = PointsSerializer(user, many=True)
        return JsonResponse(serializer.data, safe=False)

#Pesquisa dos pontos do utilizador pelo username
def searchpointsname(request, username):
    if request.method == 'GET':
        user = UserProfile.objects.filter(user__username = username)
        serializer = PointsSerializer(user, many=True)
        return JsonResponse(serializer.data, safe=False)




#Filtros
class UserFilter(filters.FilterSet):    

    class Meta:
        model = User
        fields = {
            'username',
        }
class UserProfileFilter(filters.FilterSet):    

    class Meta:
        model = UserProfile
        fields = {
            'user',
            'points',
        }
class GarrafaFilter(filters.FilterSet):    
    class Meta:
        model = Garrafa
        fields = {
            'user',
            'id'
        }


#Classes - ViewSets
class UserViewSet(viewsets.ModelViewSet):
    queryset = models.User.objects.all()
    serializer_class  = serializers.UserSerializer
    permission_class = (permissions.IsAuthenticated,)
    filter_backends = (DjangoFilterBackend,)
    filterset_class = UserFilter

class UserProfileViewSet(viewsets.ModelViewSet):
    queryset = models.UserProfile.objects.all()
    serializer_class  = serializers.UserProfileSerializer
    permission_class = (permissions.IsAuthenticated,)
    filter_backends = (DjangoFilterBackend,)
    filterset_class = UserProfileFilter

class PointsViewSet(viewsets.ModelViewSet):
    queryset = models.UserProfile.objects.all()
    serializer_class = serializers.PointsSerializer   

class GarrafaViewSet(viewsets.ModelViewSet):
    queryset = models.Garrafa.objects.all()
    serializer_class  = serializers.GarrafaSerializer
    permission_class = (permissions.IsAuthenticatedOrReadOnly,)
    filter_backends = (DjangoFilterBackend,)
    filterset_class = GarrafaFilter
 
class OleaoViewSet(viewsets.ModelViewSet):
    queryset = models.Oleao.objects.all()
    serializer_class  = serializers.OleaoSerializer
    #permission_classes = (permissions.IsAuthenticatedOrReadOnly,)
    permission_classes = (permissions.IsAdminUser,)



































